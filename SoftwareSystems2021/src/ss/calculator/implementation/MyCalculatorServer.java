package ss.calculator.implementation;

import ss.calculator.CalculatorServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyCalculatorServer implements CalculatorServer, Runnable {
    private ServerSocket ss;
    private List<MyClientHandler> handlers = new ArrayList<>();
    private Thread t;

    public MyCalculatorServer() { }

    @Override
    public void start(int port) throws IOException {
        this.ss = new ServerSocket(port);
        System.out.println("Started server at port: " + this.ss.getLocalPort());

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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean run = true;
        while (run) {
            try {
                Socket socket = ss.accept();
                MyClientHandler ch = new MyClientHandler(socket);
                handlers.add(ch);
                new Thread(ch).start();
            } catch (IOException e) {
                System.out.println("Terminated server at port: " + this.ss.getLocalPort());
                run = false;
            }
        }
    }
}
