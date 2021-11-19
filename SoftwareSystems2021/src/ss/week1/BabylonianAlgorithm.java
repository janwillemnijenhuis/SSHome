package ss.week1;

import ss.utils.TextIO;
import java.lang.Math;

public class BabylonianAlgorithm {

    public static void main(String[] args) {

        double n; // input of user

        while(true) {
            System.out.println("Please input the number:");
            n = TextIO.getlnDouble(); // user input
            if(n < 0) {
                System.out.println("Square root of negative number is impossible.");
                continue;
            }
            break;
        }

        double guess = babylonianAlgorithm(n);

        System.out.format("%.2f%n", guess);
    }

    public static double babylonianAlgorithm(double n) {

        double guess = n / 2.0;
        double r;
        double err = 5.0;

        while(err > 0.01) {
            double guess_1 = guess;
            r = n / guess;
            guess = (guess + r) / 2.0;
            err = Math.abs(guess - guess_1) / guess_1;
        }

        return guess;
    }
}