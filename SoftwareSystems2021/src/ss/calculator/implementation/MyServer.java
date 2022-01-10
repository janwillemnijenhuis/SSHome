package ss.calculator.implementation;

import org.w3c.dom.Text;
import ss.calculator.CalculatorFactory;
import ss.calculator.CalculatorServer;
import ss.utils.TextIO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyServer {

    public static void main(String[] args) {
        int port;
        do {
            System.out.println("Please provide a valid port number: ");
            port = TextIO.getlnInt();
        } while (!validPort(port));

        CalculatorFactory calculatorFactory = new MyCalculatorFactory();
        CalculatorServer server = calculatorFactory.makeCalculatorServer();

        try {
            server.start(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean run = true;
        while (run) {
            String arg = TextIO.getlnString();
            if (arg.equalsIgnoreCase("quit")) {
                System.out.println("Terminating server...");
                run = false;
            }
        }

        server.stop();
    }

    public static boolean validPort(int port) {
        return port >= 0 && port <= 65536;
    }
}
