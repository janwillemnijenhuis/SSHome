package ss.week7.chat.client;

import ss.week7.chat.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Client implements ChatClient, Runnable {
    private Socket sock;
    private ChatListener listener;
    private BufferedReader in;
    private PrintWriter out;
    private Thread t;
    private boolean run = true;

    @Override
    public boolean connect(InetAddress address, int port) {
        try {
            this.sock = new Socket(address, port);
            this.sock.setSoTimeout(1000);
            this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.out = new PrintWriter(this.sock.getOutputStream(), true);
            this.addChatListener();
            this.t = new Thread(this);
            t.start();
        } catch (IOException | NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    public void close() {
        try {
            run = false;
            removeChatListener();
            this.sock.close();
            t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendUsername(String username) {
        try {
            out.println(username);
            this.sendMessage("joined the chat.");
        } catch (Exception e) {
            this.close();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendMessage(String message) {
        try {
            out.println("SAY~" + message);
        } catch (Exception e) {
            this.close();
            return false;
        }
        return true;
    }

    @Override
    public void addChatListener() {
        this.listener = new Chat();
    }

    @Override
    public void removeChatListener() {
        this.listener = null;
    }

    @Override
    public void run() {
        String line = null;
        while (run) {
            try {
                line = in.readLine();
                if (line == null) {
                    this.close();
                } else if (line.startsWith("FROM")) {
                    String[] args = line.split("~");
                    this.listener.messageReceived(args[1], args[2]);
                }
            } catch (SocketTimeoutException | SocketException e) {
                // give time to set run to false
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
