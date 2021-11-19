package ss.week1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ss.week1.Fibonacci;

public class FibonacciTest {

    @Test
    void fibonacciWithArrayTest() {
        assertEquals(0, Fibonacci.fibonacciWithArray(0));
        assertEquals(5, Fibonacci.fibonacciWithArray(5));
        assertEquals(701408733, Fibonacci.fibonacciWithArray(44));
    }

    @Test
    void fibonacciRecursiveTest() {
        assertEquals(0, Fibonacci.fibonacciRecursive(0));
        assertEquals(5, Fibonacci.fibonacciRecursive(5));
        assertEquals(701408733, Fibonacci.fibonacciRecursive(44));
    }
}
