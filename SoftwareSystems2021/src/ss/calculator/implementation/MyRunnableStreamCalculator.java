package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.CalculatorFactory;
import ss.calculator.StreamCalculator;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class MyRunnableStreamCalculator implements Runnable {
    Reader reader;
    Writer writer;
    CalculatorFactory calculatorFactory;

    public MyRunnableStreamCalculator(Reader reader, Writer writer) {
     this.reader = reader;
     this.writer = writer;
     this.calculatorFactory = new MyCalculatorFactory();
    }

    @Override
    public void run() {
        Calculator calculator = calculatorFactory.makeCalculator();
        StreamCalculator streamCalculator = calculatorFactory.makeStreamCalculator(calculator);
        try {
            streamCalculator.process(this.reader, this.writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
