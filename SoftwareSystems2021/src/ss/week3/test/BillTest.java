package ss.week3.test;

import org.junit.jupiter.api.*;
import ss.week3.bill.StringPrinter;
import ss.week3.bill.Bill;
import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    public StringPrinter p;
    public Bill b;

    /**
     * Run this before each test case to initialize Bill, Printer and Item
     */
    @BeforeEach
    public void setUp() {
        p = new StringPrinter();
        b = new Bill(p);
    }

    /**
     * Create an implementation of Bill.Item that can return a name and price of an object
     */
    class Item implements Bill.Item {
        private double price;
        private String text;

        public Item(String text, double price) {
            this.price = price;
            this.text = text;
        }
        @Override
        public double getPrice() {
            return this.price;
        }
        @Override
        public String toString() {
            return this.text;
        }
    }

    @Test
    public void testBeginState() {
        assertEquals(0.0, b.getSum(), 0.0);
    }

    @Test
    public void testInsertedCorrectly() {
        Item banana = new Item("banana", 2.15);
        Item apple = new Item("apple", 0.90);
        assertEquals("apple", apple.toString());
        assertEquals(2.15, banana.getPrice(), 0.0);
        b.addItem(banana);
        b.addItem(apple);
        b.close();
        String output = p.getResult();
        System.out.println(output);
        assertTrue(output.contains("apple"));
    }
}