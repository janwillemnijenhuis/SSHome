package ss.calculator;

import ss.calculator.implementation.MyCalculatorFactory;
import java.io.*;

public class IOStreamReader {
    public static void main(String[] args) throws IOException {

        MyCalculatorFactory calculatorFactory = new MyCalculatorFactory();
        Calculator calculator = calculatorFactory.makeCalculator();
        StreamCalculator streamCalculator = calculatorFactory.makeStreamCalculator(calculator);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);

        streamCalculator.process(inputStreamReader, outputStreamWriter);
    }
}
