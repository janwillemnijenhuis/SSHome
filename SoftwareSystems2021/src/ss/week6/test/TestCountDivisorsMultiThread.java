package ss.week6.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.week6.CountDivisors;
import ss.week6.CountDivisorsSingleThread;

import static org.junit.Assert.assertEquals;

public class TestCountDivisorsMultiThread {
    CountDivisors cd;
    CountDivisorsSingleThread cds;

    @BeforeEach
    public void setUp() {
        cd = new CountDivisors(8);
        cds = new CountDivisorsSingleThread(1, 12);
    }

    @Test
    public void testCountDivisors() {
        cd.countDivisors();
        assertEquals(4, cd.getDivisors());
    }

    @Test
    public void testCountDivisorsSingleThread() {
        cds.loopNumbers();
        assertEquals(6, cds.getDivisors());
        assertEquals(12, cds.getNumber());
        System.out.format("Elapsed time: %.4f seconds", cds.getTime());
    }
}
