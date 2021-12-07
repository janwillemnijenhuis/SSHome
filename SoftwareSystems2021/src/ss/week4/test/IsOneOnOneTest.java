package ss.week4.test;

import org.junit.jupiter.api.Test;

import ss.week4.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsOneOnOneTest {

    @Test
    public <K, V> void testEmptyMap() {
        Map<K, V> f1 = new HashMap<K, V>();

        assertTrue(MapUtil.isOneOnOne(f1));
    }

    @Test
    public void testCorrectMap() {
        Map<Integer, Character> f2 = new HashMap<Integer, Character>();
        f2.put(1, 'a');
        f2.put(2, 'b');

        assertTrue(MapUtil.isOneOnOne(f2));
    }

    @Test
    public void testWrongMap() {
        Map<Integer, Character> f3 = new HashMap<Integer, Character>();
        f3.put(1, 'a');
        f3.put(2, 'a');

        assertFalse(MapUtil.isOneOnOne(f3));
    }
}
