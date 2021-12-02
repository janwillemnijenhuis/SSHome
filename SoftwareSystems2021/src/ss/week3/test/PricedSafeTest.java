package ss.week3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.week3.bill.Bill;
import ss.week3.hotel.PricedSafe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PricedSafeTest {
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

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
        System.setErr(new PrintStream(errContent));
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

    @Test
    public void testDeactivatedCorrectPassword() throws Exception {
        safe.deactivate();
        safe.open(CORRECT_PASSWORD);
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testDeactivatedIncorrectPassword() throws Exception {
        safe.deactivate();
        safe.open(WRONG_PASSWORD);
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testActivatedCannotBeOpened() throws Exception {
        safe.activate(CORRECT_PASSWORD);
        safe.open(WRONG_PASSWORD);
        assertFalse(safe.isOpen());
        safe.open(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertTrue(safe.isOpen());
    }

    @Test
    public void testActivatedCloseIsClosed() throws Exception {
        safe.activate(CORRECT_PASSWORD);
        safe.open(WRONG_PASSWORD);
        safe.close();
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testDeactivatedCloseIsClosed() throws Exception {
        safe.deactivate();
        safe.close();
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testOpenWithoutInput() throws Exception{
        safe.activate(CORRECT_PASSWORD);
        safe.open();
        assertEquals("State of safe not changed\n", errContent.toString());
    }

    @Test
    public void testActivateWithoutInput() throws Exception{
        safe.activate();
        assertEquals("Warning, safe not activated!\n", errContent.toString());
    }
}
