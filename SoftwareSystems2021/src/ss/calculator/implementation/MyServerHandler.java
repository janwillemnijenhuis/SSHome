package ss.calculator.implementation;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServerHandler implements Runnable {
    private InputStreamReader in;
    private OutputStreamWriter out;
    private boolean run = true;

    public MyServerHandler(Socket sock) throws IOException {
        this.in = new InputStreamReader(sock.getInputStream());
        this.out = new OutputStreamWriter(System.out);
    }

    @Override
    public void run() {
        var br = new BufferedReader(this.in);
        var pr = new PrintWriter(this.out, true);
        String line;

        while (run) {
            try {
                line = br.readLine();
                pr.println(line);
            } catch (SocketTimeoutException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        run = false;
    }
}
