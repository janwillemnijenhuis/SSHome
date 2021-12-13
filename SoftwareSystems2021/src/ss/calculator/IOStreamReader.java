package ss.calculator;

import ss.calculator.implementation.MyCalculatorFactory;
import ss.utils.TextIO;

import java.io.*;

public class IOStreamReader {
    public static void main(String[] args) throws IOException {
        MyCalculatorFactory calculatorFactory = new MyCalculatorFactory();
        Calculator calculator = calculatorFactory.makeCalculator();
        StreamCalculator streamCalculator = calculatorFactory.makeStreamCalculator(calculator);

        var pr1 = new PipedReader();
        var pw1 = new PipedWriter(pr1);
        var pr2 = new PipedReader();
        var pw2 = new PipedWriter(pr2);
        var br = new BufferedReader(pr2);
        var pw = new PrintWriter(pw1);

        boolean exit = false;
        while (!exit) {
            // this is very ugly it should be a stream but idk how a stream works
            String input = TextIO.getlnString();
            if (input.equals("exit")) {
                exit = true;
            } else {
                pw.println(input);
            }
        }
        pw.close();
        streamCalculator.process(pr1, pw2);
        String text = br.readLine();
        while (true) {
            System.out.println(text);
            text = br.readLine();
            if (text.contains("error:")) {
                break;
            }
        }
    }
}
