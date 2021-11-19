package ss.week1;

import java.util.ArrayList;

public class MostDivisors {

    public static void main(String[] args) {

        int min = 1;
        int max = 10000;

        ArrayList<Integer> n = new ArrayList<Integer>();
        int m = 0;

        for (int i = min; i <= max; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            if (count > m) {
                n.clear();
                n.add(i);
                m = count;
            }
            else if (count == m) {
                n.add(i);
            }
        }

        System.out.format("Among integers between %d and %d, %n" +
                "The maximum number of divisors was %d.%n", min, max, m);
        System.out.format("Numbers with that many divisors include: %n");

        for (int k = 0; k < n.size(); k++) {
            System.out.format("%d%n", n.get(k));
        }
    }
}