package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.DivideByZeroException;
import ss.calculator.StackEmptyException;

import java.util.Stack;

public class MakeCalculator implements Calculator {
    Stack<Double> calculatorStack;

    public MakeCalculator() {
        this.calculatorStack= new Stack<Double>();
    }

    @Override
    public void push(double value) {
        this.calculatorStack.push(value);
    }

    @Override
    public double pop() throws StackEmptyException {
        try {
            return this.calculatorStack.pop();
        } catch (Exception e) {
            throw new StackEmptyException("No elements on the calculator");
        }
    }

    @Override
    public void add() throws StackEmptyException {
        try {
            this.calculatorStack.push(this.calculatorStack.pop() + this.calculatorStack.pop());
        } catch (Exception e) {
            throw new StackEmptyException("Calculator is empty");
        }
    }

    @Override
    public void sub() throws StackEmptyException {
        try {
            double first = this.calculatorStack.pop();
            double second = this.calculatorStack.pop();
            this.calculatorStack.push(second - first);
        } catch (Exception e) {
            throw new StackEmptyException("Calculator is empty");
        }
    }

    @Override
    public void mult() throws StackEmptyException {
        try {
            this.calculatorStack.push(this.calculatorStack.pop() * this.calculatorStack.pop());
        } catch (Exception e) {
            throw new StackEmptyException("Calculator is empty");
        }
    }

    @Override
    public void div() throws DivideByZeroException, StackEmptyException {
        double first;
        double second;
        try {
            first = this.calculatorStack.pop();
            second = this.calculatorStack.pop();
        } catch (Exception e) {
            throw new StackEmptyException("Not enough items");
        }
        try {
            // is dit zo goed? want met doubles kan je wel door nul delen
            int div = (int) second / (int) first;
            this.calculatorStack.push(second / first);
        } catch (Exception e) {
            this.calculatorStack.push(Double.NaN);
            throw new DivideByZeroException("Could not divide by zero");
        }
    }

    @Override
    public void dup() throws StackEmptyException {
        try {
            this.calculatorStack.push(this.calculatorStack.peek());
        } catch (Exception e) {
            throw new StackEmptyException("Stack is empty");
        }
    }

    @Override
    public void mod() throws DivideByZeroException, StackEmptyException {
        double first;
        double second;
        try {
            first = this.calculatorStack.pop();
            second = this.calculatorStack.pop();
        } catch (Exception e) {
            throw new StackEmptyException("Not enough items");
        }
        try {
            // is dit zo goed? want met doubles kan je wel door nul delen
            int div = (int) second % (int) first;
            this.calculatorStack.push(second % first);
        } catch (Exception e) {
            this.calculatorStack.push(Double.NaN);
            throw new DivideByZeroException("Could not divide by zero");
        }
    }
}