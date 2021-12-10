package ss.week4;
import ss.week4.TransposeFunction.*;
import java.util.Arrays;

public class ComputeSortingTime {
    public static void main(String[] args) {
        int size = 50000;
        int[] test = generateInteger(size);
        int[] test2 = generateInteger(size);
        long startTime = System.nanoTime();
        Arrays.sort(test);
        long endTime = System.nanoTime();
        double seconds = (endTime-startTime) /  1000000000.0;
        System.out.format("Arrays.sort() took: %.4f seconds.\n", seconds);

        startTime = System.nanoTime();
        int[] array = selectionSort(test);
        endTime = System.nanoTime();
        seconds = (endTime-startTime) /  1000000000.0;
        System.out.format("selectionSort() took: %.4f seconds.\n", seconds);


        // now for strings
        String[] test3 = generateStrings(size);
        String[] test4 = generateStrings(size);
        startTime = System.nanoTime();
        Arrays.sort(test3);
        endTime = System.nanoTime();
        seconds = (endTime-startTime) /  1000000000.0;
        System.out.format("Arrays.sort() strings took: %.4f seconds.\n", seconds);

        startTime = System.nanoTime();
        String[] array2 = selectionSort(test4);
        endTime = System.nanoTime();
        seconds = (endTime-startTime) /  1000000000.0;
        System.out.format("selectionSort() for strings took: %.4f seconds.\n", seconds);
    }

    public static int[] generateInteger(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * 100);
        }
        return result;
    }

    public static String[] generateStrings(int size) {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomString();
        }
        return result;
    }

    private static String randomString() {
        int length = 5 + (int)(21*Math.random());
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = (char)('A' + (int)(26*Math.random()));
            str.append(ch);
        }
        return str.toString();
    }

    public static int[] selectionSort(int[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            result[i] = array[minIndex];
            array[minIndex] = array[i];
        }
        return result;
    }

    public static String[] selectionSort(String[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) == -1) {
                    minIndex = j;
                }
            }
            result[i] = array[minIndex];
            array[minIndex] = array[i];
        }
        return result;
    }
}