package ss.week7.chat;

import ss.utils.TextIO;
import ss.week7.chat.client.ChatClient;
import ss.week7.chat.client.ChatListener;
import ss.week7.chat.client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Chat implements ChatListener {
    public static void main(String[] args) {
        InetAddress address = null;
        int port = 0;
        String username = null;
        ChatClient client = new Client();

        do {
            System.out.println("Please provide a valid server address:");
            try {
                address = InetAddress.getByName(TextIO.getlnString());
            } catch (UnknownHostException e) {
                System.out.println("Not a valid server address");
                continue;
            }
            System.out.println("Please provide a valid port number:");
            port = TextIO.getlnInt();
            System.out.println("Type your name:");
            username = TextIO.getlnString();
        } while (!client.connect(address, port));

        client.sendUsername(username);
        String line;
        System.out.println("Type chat messages, send by ENTER");
        System.out.println("Type quit to exit");
        while (!(line = TextIO.getlnString()).equals("quit")) {
            client.sendMessage(line);
        }
        System.out.println("Exiting...");
        client.close();
    }

    @Override
    public void messageReceived(String from, String message) {
        System.out.println(from + ": " + message);
    }
}
