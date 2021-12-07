package ss.calculator;

import java.io.Reader;
import java.io.Writer;

/**
 * This interface defines a factory service to make implementations of the calculator variants
 */
public interface CalculatorFactory {
    /**
     * Make a new calculator
     * @return an implementation of the calculator
     */
    Calculator makeCalculator();

    /**
     * Make a new calculator that reads from a stream and writes to a stream
     * @return an implementation of the stream calculator
     */
    StreamCalculator makeStreamCalculator(Calculator calculator);

    /**
     * Make a new Runnable that, when run, reads instructions from the reader and writes
     * output to the writer.
     * @param reader the reader to read instructions from
     * @param writer the writer to write output (error messages and popped values)
     * @return a Runnable that wraps a stream calculator
     */
    Runnable makeRunnableStreamCalculator(Reader reader, Writer writer);

    /**
     * Make a CalculatorServer object that can start a Java Socket server
     * @return an implementation of the calculator server
     */
    CalculatorServer makeCalculatorServer();
}
