package ss.week3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.week3.bill.Bill;
import ss.week3.hotel.PricedSafe;

public class PricedSafeTest {

    private PricedSafe safe;
    private static final double PRICE = 6.36;
    private static final String PRICE_PATTERN = ".*6[.,]36.*";

    public String CORRECT_PASSWORD;
    public String WRONG_PASSWORD;


    @BeforeEach
    public void setUp() throws Exception {
        safe = new PricedSafe(PRICE);
        CORRECT_PASSWORD = safe.getPassword().getInitPass();
        WRONG_PASSWORD = CORRECT_PASSWORD + "WRONG";
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testIsBillItem() throws Exception {
        assertTrue(safe instanceof Bill.Item,
                "safe should be an instance of Bill.Item.");
    }

    @Test
    public void testDoesGetPriceWork() {
        assertEquals(PRICE, safe.getPrice(), 0,
                "GetPrice should return the price of the safe.");
    }

    @Test
    public void testToString() {
        assertTrue(safe.toString().contains(Double.toString(PRICE)), "toString should contain the price of the safe");
    }

    @Test
    public void testCorrectPassword() throws Exception {
        safe.activate(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testIncorrectPassword() throws Exception {
        safe.activate(WRONG_PASSWORD);
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }
}
