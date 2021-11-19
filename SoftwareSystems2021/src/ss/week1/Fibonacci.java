package ss.week1;

import java.util.ArrayList;
import ss.utils.TextIO;

public class Fibonacci {

    public static void main(String[] args) {

        int idx; // init the index requested by the user

        while (true) {
            System.out.println("Please input the index of the Fibonacci sequence:");
            idx = TextIO.getlnInt(); // user input index
            if (idx >= 0) {
                break;
            }
            System.out.println("An index should be positive, please try again.");
        }

        long m = fibonacciRecursive(idx);

        long l = fibonacciWithArray(idx);

        System.out.format("1. The value from fibonacciRecursive for index %d is: %d%n", idx, m);
        System.out.format("2. The value from fibonacciWithArray for index %d is: %d%n", idx, l);
    }

    public static long fibonacciRecursive(int k) {
        if (k == 0) {
            return 0;
        }
        else if(k == 1 || k == 2) {
            return 1;
        }
        return fibonacciRecursive(k - 1) + fibonacciRecursive(k - 2);
    }

    public static long fibonacciWithArray(int k) {

        ArrayList<Integer> f = new ArrayList<Integer>();

        f.add(0); // init first two values of Fibonacci sequence
        f.add(1);

        for (int i = 2; i <= k; i++) {
            f.add(f.get(i - 1) + f.get(i - 2));
        }
        if(k == 0) {
            return f.get(0);
        }
        else {
            return f.get(f.size() - 1);
        }
    }
}