package ss.week5.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ss.week5.PhoneDirectory;

public class PhoneDirectoryTest {
    PhoneDirectory phoneDirectory;

    @BeforeEach
    public void setUp() {
        this.phoneDirectory = new PhoneDirectory();
    }

    @Test
    public void testEmptyPhoneDirectory() {
        assertNull(phoneDirectory.getNumber("Jan"));
    }

    @Test
    public void testNumberInDirectory() {
        phoneDirectory.putNumber("Jan", "0612345678");
        assertEquals("0612345678", phoneDirectory.getNumber("Jan"));
    }
}
