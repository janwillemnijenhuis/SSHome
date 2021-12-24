package ss.calculator.implementation;

import ss.calculator.Calculator;
import ss.calculator.DivideByZeroException;
import ss.calculator.StackEmptyException;
import ss.calculator.StreamCalculator;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class MyStreamCalculator implements StreamCalculator {
    Calculator calculator;

    public MyStreamCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void process(Reader input, Writer output) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(input);
        PrintWriter pw = new PrintWriter(output, true);
        String line = null;
        try {
            outerloop:
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
                    try {
                        number = Double.parseDouble(contents[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        pw.println("error: please add a value after the whitespace");
                        continue;
                    }
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
                        this.calculator.add();
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
                    case "quit":
                        pw.println("terminating...");
                        break outerloop;
                    default:
                        pw.println("error: invalid command");
                        break;
                }
            }
            br.close();
        } catch (StackEmptyException | IOException | NumberFormatException | DivideByZeroException e) {
            pw.println(e.getMessage());
            pw.println("terminating...");
        } finally {
            pw.close();
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        }
    }
}
