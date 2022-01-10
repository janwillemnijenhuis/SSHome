package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.CalculatorServer;
import ss.calculator.StreamCalculator;

import java.io.Reader;
import java.io.Writer;

public class MyCalculatorFactory implements ss.calculator.CalculatorFactory {

    @Override
    public Calculator makeCalculator() {
        return new MyCalculator();
    }

    @Override
    public StreamCalculator makeStreamCalculator(Calculator calculator) {
        return new MyStreamCalculator(calculator);
    }

    @Override
    public Runnable makeRunnableStreamCalculator(Reader reader, Writer writer) {
        return new MyRunnableStreamCalculator(reader, writer);
    }

    @Override
    public CalculatorServer makeCalculatorServer() {
        return new MyCalculatorServer();
    }
}