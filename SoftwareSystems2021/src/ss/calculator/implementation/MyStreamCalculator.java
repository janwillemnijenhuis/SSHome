package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.DivideByZeroException;
import ss.calculator.StackEmptyException;
import ss.calculator.StreamCalculator;

import java.io.*;

public class MyStreamCalculator implements StreamCalculator {
    Calculator calculator;

    public MyStreamCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void process(Reader input, Writer output) throws IOException {
        BufferedReader br = new BufferedReader(input);
        PrintWriter pw = new PrintWriter(output, true);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String command;
                double number = 0;
                if (line.equals("")) {
                    pw.println("error: empty line not allowed");
                    continue;
                }
                if (line.contains(" ")) {
                    String[] contents = line.split(" ");
                    command = contents[0];
                    number = Double.parseDouble(contents[1]);
                } else {
                    command = line;
                }
                switch (command) {
                    case "push":
                        if (line.split(" ").length == 2) {
                            this.calculator.push(number);
                        } else {
                            pw.println("error: this operation is not allowed");
                        }
                        break;
                    case "pop":
                        pw.println(this.calculator.pop());
                        break;
                    case "add":
                        try {
                            this.calculator.add();
                        } catch (StackEmptyException e) {
                            pw.println("error: stack is empty");
                        }
                        break;
                    case "sub":
                        this.calculator.sub();
                        break;
                    case "mult":
                        this.calculator.mult();
                        break;
                    case "div":
                        this.calculator.div();
                        break;
                    case "dup":
                        this.calculator.dup();
                        break;
                    case "mod":
                        this.calculator.mod();
                        break;
                    default:
                        pw.println("error: invalid command");
                        break;
                }
            }
            br.close();
        } catch (StackEmptyException | IOException | NumberFormatException | DivideByZeroException e) {
            pw.println(e.getMessage());
        }
        // i now add it here to ensure user doesnt go to far, does that make sense? edit - or should i do null
        pw.println("error: the stack is empty");
        pw.close();
    }
}
