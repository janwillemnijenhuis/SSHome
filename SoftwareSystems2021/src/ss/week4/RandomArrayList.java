package ss.week4;

import java.util.ArrayList;

public class RandomArrayList {
    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Integer> randomIntegers = randomIntegers(10, 100);
        System.out.printf(String.valueOf(randomIntegers));
    }

    /**
     * generates arraylist with random ints
     * @param size length of arraylist
     * @param max value in (1, max)
     * @return arraylist
     */
    /*@
        requires size instanceof int;
        requires max instanceof int;
        requires max > 1;
        requires size > 1;
        ensures \result instanceof ArrayList;
    */
    public static ArrayList randomIntegers(int size, int max) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            result.add((int) (Math.random() * (max + 1)));
        }
        return result;
    }
}