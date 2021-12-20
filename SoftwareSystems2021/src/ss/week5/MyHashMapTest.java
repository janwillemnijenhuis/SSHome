package ss.week5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {
    MyHashMap myHashMap;

    @BeforeEach
    void setUp() {
        myHashMap = new MyHashMap();
    }

    @Test
    void testEmptyHashMap() {
        assertEquals(0, myHashMap.size());
        assertTrue(myHashMap.get("Jan").contains("error"));
    }

    @Test
    void testAddElement() {
        myHashMap.put("Jan", "23");
        myHashMap.put("Anton", "25");
        assertEquals("23", myHashMap.get("Jan"));
        assertEquals("25", myHashMap.get("Anton"));
        assertEquals(2, myHashMap.size());
    }

    @Test
    void testInsertExistingKey() {
        myHashMap.put("Jan", "23");
        myHashMap.put("Anton", "25");
        myHashMap.put("Jan", "30");
        assertEquals(2, myHashMap.size());
        assertEquals("30", myHashMap.get("Jan"));
    }

    @Test
    void testGetNonexistentKey() {
        myHashMap.put("Jan", "23");
        assertTrue(myHashMap.get("Anton").contains("error"));
    }
}
