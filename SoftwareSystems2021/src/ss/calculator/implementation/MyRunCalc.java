package ss.calculator.implementation;

import ss.calculator.CalculatorFactory;

import java.io.*;

public class MyRunCalc {
    public static void main(String[] args) throws IOException {

        CalculatorFactory calculatorFactory = new MyCalculatorFactory(); // create a new calculatorfactory

        var br = new BufferedReader(new InputStreamReader(System.in)); // to read from I/O
        OutputStreamWriter or = new OutputStreamWriter(System.out); // to write to I/O
        var pr1 = new PipedReader(); // to process input to the calculator
        var pw1 = new PipedWriter(pr1); // to process input to the calculator
        var pw = new PrintWriter(pw1); // to write input to the pipedstream

        var rsc = calculatorFactory.makeRunnableStreamCalculator(pr1, or); // the runnablecalculator

        var thread = new Thread(rsc); // init a thread containing this calculator

        thread.start(); // start the thread

        String line; // init empty string to put line from I/O in
        while ((line = br.readLine()) != null && !line.equals("quit")) {
            // check if line is not null and line does not equal quit, otherwise terminate
            pw.println(line); // print the line towards the calculator
        }

        // close the printwriter and outputstreamwriter
        pw.close();
        or.close();
    }
}