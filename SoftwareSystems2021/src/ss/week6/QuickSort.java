package ss.week6;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuickSort extends RecursiveAction {

    private int first;
    private int last;
    private int[] a;

    public QuickSort(int[] a) {
        this(a, 0, a.length - 1);
    }

    public QuickSort(int[] a, int first, int last) {
        this.a = a;
        this.first = first;
        this.last = last;
    }

    @Override
    protected void compute() {
        if (first < last) {

            int position = partition(a, first, last);

            invokeAll(new QuickSort(a, first, position),
                    new QuickSort(a, position + 1, last));
        }
    }

    public static int partition(int[] a, int first, int last) {
        int mid = (first + last) / 2;
        int pivot = a[mid];
        swap(a, mid, last); // put pivot at the end of the array
        int pi = first;
        int i = first;
        while (i != last) {
            if (a[i] < pivot) {
                swap(a, pi, i);
                pi++;
            }
            i++;
        }
        swap(a, pi, last); // put pivot in its place "in the middle"
        return pi;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {4,2,3,1,5,323,56,6,7,3,2,68,0};
        QuickSort quickSort = new QuickSort(a);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(quickSort);
        System.out.println(Arrays.toString(a));
    }
}

