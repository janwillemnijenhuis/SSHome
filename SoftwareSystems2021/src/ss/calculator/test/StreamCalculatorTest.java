package ss.calculator.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ss.calculator.CalculatorFactory;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


@Disabled
class StreamCalculatorTest {
    private CalculatorFactory factory;

    @BeforeEach
    void setup() {

    }

    /**
     * Simple test if the stream calculator can do some basic operations
     */
    @Test
    void fileAndStringTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var fr = new FileReader("src/ss/calculator/test/calculatorinstructions")) {
            // stringwriter! Basically writes to a string
            var sw = new StringWriter();
            streamCalculator.process(fr, sw);
            // after we get the string, we just use a stringreader around the string :-)
            // an alternative is to use piped streams
            try (var br = new BufferedReader(new StringReader(sw.toString()))) {
                assertEquals("-120.0", br.readLine());
                assertEquals("34.5", br.readLine());
                assertEquals("400.0", br.readLine());
                assertEquals("0.025", br.readLine());
            }
        }
    }

    /**
     * Test if a the streamCalculator handles a closed output stream
     */
    @Test
    void closedWriterTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        // Here we use a try-with-resources block...
        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var pw = new PrintWriter(pw1)) {
            pw.println("push 1");
            pw.println("push 0");
            pw.println("div");
            pw.println("pop");
            pw.close();
            pr2.close();
            pw2.close();
            // the output streams are closed. what will happen? (the process method should not crash/stacktrace)
            streamCalculator.process(pr1, pw2);
        }
    }

    /**
     * Test if a bad push syntax results in an error message
     */
    @Test
    void invalidPushTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        // Here we use a try-with-resources block...
        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push"); // this is a bug. push should have a parameter
            pw.println("push 0");
            pw.println("add");
            pw.println("pop");
            pw.close();
            // if this method call crashes, then push is not checked properly
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if an empty line results in an error message
     */
    @Test
    void emptyLineTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        // Here we use a try-with-resources block...
        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1");
            pw.println("push 0");
            pw.println(""); // this is an invalid command, should report an error
            pw.println("pop");
            pw.close();
            // if this method call crashes, then push is not checked properly
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if an invalid command results in an error message
     */
    @Test
    void invalidCommandTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        // Here we use a try-with-resources block...
        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1");
            pw.println("push 0");
            pw.println("blablabla"); // this is an invalid command, should report an error
            pw.println("pop");
            pw.close();
            // if this method call crashes, then push is not checked properly
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if a divide by zero results in an error message
     */
    @Test
    void divideByZeroTest() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        // Here we use a try-with-resources block...
        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1");
            pw.println("push 0");
            pw.println("div");
            pw.println("pop");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if pop() on an empty stack results in an error message
     */
    @Test
    void emptyStackTestPop() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1");
            pw.println("push 0");
            pw.println("pop");
            pw.println("pop");
            pw.println("pop");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertEquals("0.0", s);
            s = br.readLine();
            assertNotNull(s);
            assertEquals("1.0", s);
            s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if add() on an empty stack results in an error message
     */
    @Test
    void emptyStackTestAdd() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1.5");
            pw.println("push 0.5");
            pw.println("add");
            pw.println("pop");
            pw.println("push 0");
            pw.println("add");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertEquals("2.0", s);
            s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if sub() on an empty stack results in an error message
     */
    @Test
    void emptyStackTestSub() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1.5");
            pw.println("push 0.5");
            pw.println("sub");
            pw.println("pop");
            pw.println("push 0");
            pw.println("sub");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertEquals("1.0", s);
            s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if mult() on an empty stack results in an error message
     */
    @Test
    void emptyStackTestMult() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1.5");
            pw.println("push 0.5");
            pw.println("mult");
            pw.println("pop");
            pw.println("push 0");
            pw.println("mult");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertEquals("0.75", s);
            s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }

    /**
     * Test if div() on an empty stack results in an error message
     */
    @Test
    void emptyStackTestDiv() throws IOException {
        var calculator = factory.makeCalculator();
        var streamCalculator = factory.makeStreamCalculator(calculator);

        try (var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
             var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
             var br = new BufferedReader(pr2); var pw = new PrintWriter(pw1)) {
            pw.println("push 1.5");
            pw.println("push 0.5");
            pw.println("div");
            pw.println("pop");
            pw.println("push 0");
            pw.println("div");
            pw.close();
            streamCalculator.process(pr1, pw2);
            String s = br.readLine();
            assertNotNull(s);
            assertEquals("3.0", s);
            s = br.readLine();
            assertNotNull(s);
            assertTrue(s.startsWith("error: "));
        }
    }
}
