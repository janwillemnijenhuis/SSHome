package ss.calculator.implementation;

import ss.utils.TextIO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import static ss.calculator.implementation.MyServer.validPort;

public class MyClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Please provide a server address:");
        String address = TextIO.getlnString();

        int port;
        do {
            System.out.println("Please provide a valid port number: ");
            port = TextIO.getlnInt();
        } while (!validPort(port));

        Socket sock = null;
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            sock = new Socket(inetAddress, port);
            sock.setSoTimeout(1000);
            System.out.println("Started connection on port: " + port);
        } catch (IOException e) {
            System.err.println("Could not connect to this server and port number");
            System.exit(1);
        }

        //var in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        var io = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(sock.getOutputStream(), true);

        boolean run = true;

        MyServerHandler handler = new MyServerHandler(sock);
        Thread t = new Thread(handler);
        t.start();

        while (run) {
            try {
                String msg = io.readLine();
                if (msg.equalsIgnoreCase("quit")) {
                    run = false;
                    handler.stop();
                    continue;
                }
                out.println(msg);
            } catch (IOException e) {
                run = false;
            }
        }

        handler.stop();
        t.join();
        System.out.println("Client disconnected");
        sock.close();
        return;
    }
}
