package ss.calculator.implementation;

import java.io.*;
import java.net.Socket;

public class MyClientHandler extends MyRunnableStreamCalculator {

    public MyClientHandler(Socket socket) throws IOException {
        super(new InputStreamReader(socket.getInputStream()), new OutputStreamWriter(socket.getOutputStream()));
    }

}
