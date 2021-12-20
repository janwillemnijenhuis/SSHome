package ss.week5;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class TestPredicates {
    Predicates predicates;
    EqualsFive lessThanFive;

    public class EqualsFive implements Predicate<Integer> {
        @Override
        public boolean test(Integer integer) {
            Integer five = 5;
            return integer <= five;
        }
    }

    @BeforeEach
    void setUp() {
        predicates = new Predicates();
        lessThanFive = new EqualsFive();
    }

    @Test
    void testRemovePredicate() {
        List<Integer> coll = new ArrayList<>(Arrays.asList(3, 10, 5));
        predicates.remove(coll, lessThanFive);
        assertFalse(coll.contains(5));
    }

    @Test
    void testRetainPredicate() {
        List<Integer> coll = new ArrayList<>(Arrays.asList(3, 10, 5));
        predicates.retain(coll, lessThanFive);
        assertTrue(coll.contains(5));
    }

    @Test
    void testListOfElements() {
        List<Integer> coll = new ArrayList<>(Arrays.asList(3, 10, 5));
        List<Integer> correct = predicates.collect(coll, lessThanFive);
        assertTrue(correct.contains(5));
        assertTrue(correct.contains(3));
    }

    @Test
    void testIndexOfElements() {
        ArrayList<Integer> coll = new ArrayList<>(Arrays.asList(3, 10, 5));
        int index = predicates.find(coll, lessThanFive);
        assertEquals(0, index);
    }
}
