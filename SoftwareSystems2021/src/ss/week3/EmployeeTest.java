package ss.week3;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class EmployeeTest {
    FixedEmployee frank;
    OvertimeEmployee hank;
    OvertimeEmployee shank;
    @BeforeEach
    void setup() {
        frank = new FixedEmployee(175);
        hank = new OvertimeEmployee(200);
        shank = new OvertimeEmployee(100);

    }

    @Test
    void testHours() {
        assertEquals(frank.hours(), 175);
        assertEquals(hank.hours(), 200);
        assertEquals(shank.hours(), 100);
    }

    @Test
    void testPayment() {
        int frankSalary = 175 * 20;
        int hankSalary = 200 * 20 + (int) ((200 - 160) * 20 * 0.5);
        int shankSalary = 100 * 20;

        assertEquals(frank.pay(), frankSalary);
        assertEquals(hank.pay(), hankSalary);
        assertEquals(shank.pay(), shankSalary);
    }
}