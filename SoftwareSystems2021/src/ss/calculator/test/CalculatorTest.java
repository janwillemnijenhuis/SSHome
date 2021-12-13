package ss.calculator.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.calculator.Calculator;
import ss.calculator.DivideByZeroException;
import ss.calculator.StackEmptyException;
import ss.calculator.implementation.MyCalculatorFactory;
//import ss.calculator.reference.MyCalculatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setup() {
        this.calculator = new MyCalculatorFactory().makeCalculator();
    }

    @Test
    void testPushPop() throws StackEmptyException {
        // Simple test: if we push the value 0.5, do we also get it back?
        calculator.push(0.5);
        assertEquals(0.5, calculator.pop());

        // Test a bunch of random numbers in a random order...
        List<Double> exampleNumbers = new ArrayList<>();
        var rand = new Random();
        for (int i=0; i<10; i++) {
            exampleNumbers.add(rand.nextDouble());
        }

        for (int i=0; i<10; i++) {
            calculator.push(exampleNumbers.get(i));
        }

        for (int i=9; i>=0; i--) {
            assertEquals(exampleNumbers.get(i), calculator.pop(), 0.01);
        }

        // Test exceptions
        assertThrows(StackEmptyException.class, () -> calculator.pop());
    }

    @Test
    void testAdd() throws StackEmptyException {
        // Also test proper handling of the stack
        // after these, stack will be: { 100, 200, -100, -300 }
        // so the first add adds -100 and -300 (= -400)
        // and the second add adds 100 and 200 (= 300)
        calculator.push(100);
        calculator.push(200);
        calculator.push(-100);
        calculator.push(-300);
        calculator.add();
        assertEquals(-400, calculator.pop());
        calculator.add();
        assertEquals(300, calculator.pop());

        // Now add some numbers
        calculator.push(4);
        calculator.push(7);
        calculator.push(1);
        calculator.push(8);
        calculator.push(3);
        calculator.push(5);
        calculator.push(2);
        calculator.push(9);
        calculator.push(6);
        calculator.add();
        calculator.add();
        calculator.add();
        calculator.add();
        calculator.add();
        calculator.add();
        calculator.add();
        calculator.add();
        // adding 1+2+3+4+5+6+7+8+9 (in any order) = 45
        assertEquals(45, calculator.pop());
        assertThrows(StackEmptyException.class, () -> calculator.pop());
    }

    @Test
    void testSub() throws StackEmptyException {
        // test if 100-0=100
        calculator.push(100);
        calculator.push(0);
        calculator.sub();
        assertEquals(100, calculator.pop());

        // test if 0-100=-100
        calculator.push(0);
        calculator.push(100);
        calculator.sub();
        assertEquals(-100, calculator.pop());
    }

    @Test
    void testMult() throws StackEmptyException {
        calculator.push(5);
        calculator.push(9);
        calculator.mult();
        assertEquals(45, calculator.pop());
        assertThrows(StackEmptyException.class, () -> calculator.pop());
    }

    @Test
    void testDiv() throws StackEmptyException, DivideByZeroException {
        // test if we get a divide by zero exception
        calculator.push(100);
        calculator.push(0);
        assertThrows(DivideByZeroException.class, () -> calculator.div());
        // after a divide by zero exception, the stack should be size 1 with only Double.NaN on top
        assertEquals(Double.NaN, calculator.pop());
        assertThrows(StackEmptyException.class, () -> calculator.pop());

        // now test a proper division 100/6
        calculator.push(100);
        calculator.push(6);
        calculator.div();
        assertEquals(100.0/6, calculator.pop());
        assertThrows(StackEmptyException.class, () -> calculator.pop()); // and test if stack now empty
    }

    @Test
    void testDup() throws StackEmptyException {
        // push 100 to the stack, and duplicate it
        calculator.push(100);
        calculator.dup();
        // check that there is 100 on top of the stack twice
        assertEquals(calculator.pop(), 100, 0.01);
        assertEquals(calculator.pop(), 100, 0.01);
        // assert that it throws a stackemptyexception
        assertThrows(StackEmptyException.class, () -> calculator.pop()); // and test if stack now empty
    }

    @Test
    void testMod() throws DivideByZeroException, StackEmptyException {
        // push 100 and 5 to the stack, then push the remainder after division to the stack
        calculator.push(100);
        calculator.push(3);
        calculator.mod();
        // check if the result is 1
        assertEquals(calculator.pop(), 1, 0.01);
        assertThrows(StackEmptyException.class, () -> calculator.pop()); // and test if stack now empty
        // now test if the stack throws dividebyzeroexception when dividing by 0
        calculator.push(100);
        calculator.push(0);
        assertThrows(DivideByZeroException.class, () -> calculator.mod());
        assertEquals(Double.NaN, calculator.pop());
        // and check if its empty
        assertThrows(StackEmptyException.class, () -> calculator.pop());
    }
}
