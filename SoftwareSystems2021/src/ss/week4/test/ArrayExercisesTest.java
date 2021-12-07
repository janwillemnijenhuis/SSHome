package ss.week4.test;

import ss.week4.ArrayExercises;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;



public class ArrayExercisesTest {
    @Test
    public void testCountNegativeNumbers() {
        assertEquals(0, ArrayExercises.countNegativeNumbers(new int[]{}));
        assertEquals(0, ArrayExercises.countNegativeNumbers(new int[]{1}));
        assertEquals(3, ArrayExercises.countNegativeNumbers(new int[]{1,2,3,0,-1,-2,-3}));
    }

    @Test
    public void testReverseArrayEmptyArray() {
        int[] array = {};
        ArrayExercises.reverseArray(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    public void testReverseArrayOneElement() {
        int[] array = {1};
        ArrayExercises.reverseArray(array);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    public void testReverseArrayEvenElements() {
        int[] array = {1, 2, 3, 4};
        ArrayExercises.reverseArray(array);
        assertArrayEquals(new int[]{4, 3, 2, 1}, array);
    }

    @Test
    public void testReverseArrayUnevenElements() {
        int[] array = {1, 2, 3, 4, 5};
        ArrayExercises.reverseArray(array);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, array);
    }
}
