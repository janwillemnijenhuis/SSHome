package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.CalculatorFactory;
import ss.calculator.CalculatorServer;
import ss.calculator.StreamCalculator;

import java.io.Reader;
import java.io.Writer;

public class ImplCalculatorFactory implements CalculatorFactory {

    @Override
    public Calculator makeCalculator() {
        return new MakeCalculator();
    }

    @Override
    public StreamCalculator makeStreamCalculator(Calculator calculator) {
        return null;
    }

    @Override
    public Runnable makeRunnableStreamCalculator(Reader reader, Writer writer) {
        return null;
    }

    @Override
    public CalculatorServer makeCalculatorServer() {
        return null;
    }
}