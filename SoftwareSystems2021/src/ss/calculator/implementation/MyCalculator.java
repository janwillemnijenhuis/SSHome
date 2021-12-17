package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.DivideByZeroException;
import ss.calculator.StackEmptyException;

import java.util.ArrayList;
import java.util.Stack;

public class MyCalculator implements Calculator {
    ArrayList<Double> calculatorArray;

    public MyCalculator() {
        this.calculatorArray= new ArrayList<Double>();
    }

    @Override
    public void push(double value) {
        // so value to leave first is at the end
        this.calculatorArray.add(value);
    }

    @Override
    public double pop() throws StackEmptyException {
        if (calculatorArray.size() >= 1) {
            double temp = this.calculatorArray.get(this.calculatorArray.size() - 1);
            this.calculatorArray.remove(calculatorArray.size() - 1);
            return temp;
        } else {
            throw new StackEmptyException("error: stack is empty");
        }
    }

    @Override
    public void add() throws StackEmptyException {
        if (calculatorArray.size() >= 2) {
            double first = pop();
            double second = pop();
            push(first + second);
        } else {
            throw new StackEmptyException("error: stack contains not enough arguments");
        }
    }

    @Override
    public void sub() throws StackEmptyException {
        if (this.calculatorArray.size() >= 2) {
            double first = pop();
            double second = pop();
            push(second - first);
        } else {
            throw new StackEmptyException("error: calculator is empty");
        }
    }

    @Override
    public void mult() throws StackEmptyException {
        try {
            push(pop() * pop());
        } catch (Exception e) {
            throw new StackEmptyException("error: calculator is empty");
        }
    }

    @Override
    public void div() throws DivideByZeroException, StackEmptyException {
        double first;
        double second;
        try {
            first = pop();
            second = pop();
        } catch (Exception e) {
            throw new StackEmptyException("error: not enough items");
        }
        if (first != 0.0) {
            push(second / first);
        } else {
            push(Double.NaN);
            throw new DivideByZeroException("error: could not divide by zero");
        }
    }

    @Override
    public void dup() throws StackEmptyException {
        if (this.calculatorArray.size() >= 1) {
            push(this.calculatorArray.get(this.calculatorArray.size() - 1));
        } else {
            throw new StackEmptyException("error: stack is empty");
        }
    }

    @Override
    public void mod() throws DivideByZeroException, StackEmptyException {
        double first;
        double second;
        try {
            first = pop();
            second = pop();
        } catch (Exception e) {
            throw new StackEmptyException("error: not enough items");
        }
        if (first != 0) {
            push(second % first);
        } else {
            push(Double.NaN);
            throw new DivideByZeroException("error: could not divide by zero");
        }
    }
}