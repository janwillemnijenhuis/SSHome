package ss.week1;

import ss.utils.TextIO;
import java.util.Arrays;

/*
    Write a program that will evaluate simple expressions such as 17 + 3 and 3.14159 * 4.7. (solution)
    The expressions are to be typed in by the user. The input always consists of a number,
    followed by an operator, followed by another number. The operators that are allowed are
    +, -, *, and /. You can read the numbers with TextIO.getDouble() and the operator
    with TextIO.getChar(). Your program should read an expression, print its value, read
    another expression, print its value, and so on. The program should end when the user
    enters 0 as the first number on the line.
 */

public class SimpleExpressions {
    public static void main(String[] args) {
        while(true) {
            double result = 0;
            System.out.println("Please provide an input in the following format on the line below: NUMBER OPERATOR NUMBER. Enter 0 to exit the program.");
            String[] input = TextIO.getlnString().split(" ");
            double firstNum = Double.parseDouble(input[0]);
            if (firstNum == 0.0) {
                break;
            }
            double secondNum = Double.parseDouble(input[2]);
            String operator = input[1];
            String[] valid = {"+", "-", "*", "/"};
            boolean validOperator = Arrays.asList(valid).contains(operator);
            if(validOperator) {
                int idx = Arrays.asList(valid).indexOf(operator);
                switch(idx) {
                    case 0:
                        result = firstNum + secondNum;
                        break;
                    case 1:
                        result = firstNum - secondNum;
                        break;
                    case 2:
                        result = firstNum * secondNum;
                        break;
                    case 3:
                        result = firstNum / secondNum;
                        break;
                    default:
                        System.out.println("An unexpected error occured, please input again.");
                        break;
                }
            }
            else {
                System.out.println("Please input a valid operator.");
            }
            System.out.format("%.2f%n", result);
        }
    }
}