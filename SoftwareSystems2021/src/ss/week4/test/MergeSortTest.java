package ss.week4.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ss.week4.MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MergeSortTest {
    @Test
    public void testMergesortEmptyList() {
        List<Integer> sequence = new ArrayList<>(Collections.emptyList());
        List<Integer> result = MergeSort.mergeSort(sequence);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testMergesortSingleItem() {
        List<Integer> sequence = new ArrayList<>(Arrays.asList(1));
        List<Integer> result = MergeSort.mergeSort(sequence);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testMergesortSortedList() {
        List<Integer> sequence = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> result = MergeSort.mergeSort(sequence);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);

        sequence = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        result = MergeSort.mergeSort(sequence);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }

    @Test
    public void testMergesortUnsortedList() {
        List<Integer> sequence = new ArrayList<>(Arrays.asList(3, 2, 1, 5, 4));
        List<Integer> result = MergeSort.mergeSort(sequence);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);

        sequence = new ArrayList<>(Arrays.asList(3, 2, 1, 6, 5, 4));
        result = MergeSort.mergeSort(sequence);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }
}
