package ss.week7.chat.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyChatServer implements ChatServer, Runnable {
    private int port;
    private ServerSocket ss;
    private Thread t;
    private ArrayList<ChatClientHandler> clients = new ArrayList<>();

    public MyChatServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        MyChatServer server = new MyChatServer(8080);
        server.start();
    }

    @Override
    public void start() {
        try {
            this.ss = new ServerSocket(port);
            System.out.println("Starting server at port " + this.ss.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

        t = new Thread(this);
        t.start();
    }

    @Override
    public int getPort() {
        return this.ss.getLocalPort();
    }

    @Override
    public void stop() {
        try {
            ss.close();
            t.join();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean run = true;
        int threadCount = 0;
        while (run) {
            try {
                Socket sock = ss.accept();
                System.out.println("Accepted new client.");
                var ch = new ChatClientHandler(sock, this);
                threadCount += 1;
                new Thread(ch).start();
            } catch (IOException e) {
                System.out.println("Terminating server...");
                run = false;
            }
        }
    }

    synchronized public void addClient(ChatClientHandler ch) {
        clients.add(ch);
    }

    synchronized public void removeClient(ChatClientHandler ch) {
        clients.remove(ch);
    }

    synchronized public void handleChatMessage(ChatClientHandler from, String message) {
        String username = from.getUsername();
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).sendChat(username, message);
        }
    }
}







