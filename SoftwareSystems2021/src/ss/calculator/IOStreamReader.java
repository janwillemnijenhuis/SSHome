package ss.calculator;

import ss.calculator.implementation.MyCalculatorFactory;
import java.io.*;

public class IOStreamReader {
    public static void main(String[] args) throws IOException {

        MyCalculatorFactory calculatorFactory = new MyCalculatorFactory();
        Calculator calculator = calculatorFactory.makeCalculator();
        StreamCalculator streamCalculator = calculatorFactory.makeStreamCalculator(calculator);

        var sr = new BufferedReader(new InputStreamReader(System.in));
        var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
        var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
        var pw = new PrintWriter(pw1); var br = new BufferedReader(pr2);

        boolean exit = false;
        String line = null;
        while (!exit) {
            try {
                line = sr.readLine();
                if (line.equals("compute")) {
                    pw.close();
                    exit = true;
                }
                pw.write(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException();
            }
        }
        streamCalculator.process(pr1, pw2);
        String s = null;
        while (!(s = br.readLine()).contains("error: the stack is empty")) {
            System.out.println(s);
        }
    }
}
