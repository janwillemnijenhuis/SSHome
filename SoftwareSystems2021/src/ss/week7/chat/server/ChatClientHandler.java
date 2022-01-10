package ss.week7.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable {
    private BufferedReader br;
    private PrintWriter pw;
    private Socket sock;
    private String username;
    private MyChatServer server;

    public ChatClientHandler(Socket sock, MyChatServer server) throws IOException {
        this.sock = sock;
        this.server = server;
        this.br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.pw = new PrintWriter(sock.getOutputStream(), true);
    }

    @Override
    public void run() {
        String line;
        boolean run = true;
        while (run) {
            try {
                line = br.readLine();
                if (line == null) {
                    run = false;
                    this.close();
                    this.server.removeClient(this);
                } else if (this.username == null) {
                    this.username = line;
                    this.server.addClient(this);
                }  else {
                    String[] args = line.split("~");
                    if (args[0].equals("SAY") && args.length == 2) {
                        this.server.handleChatMessage(this, args[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                this.close();
                this.server.removeClient(this);
            }
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void close() {
        try {
            this.sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChat(String from, String message) {
        String protoMessage =  "FROM~" + from + "~" + message;
        try {
            this.pw.println(protoMessage);
        } catch (Exception e) {
            this.close();
            this.server.removeClient(this);
        }
    }
}
