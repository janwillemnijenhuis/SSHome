package ss.week3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.week3.bill.Bill;
import ss.week3.bill.StringPrinter;
import ss.week3.hotel.*;
import ss.week3.password.Password;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    /** Test variable for a <tt>Hotel</tt> object. */
    private Hotel hotel;
    private String correctPassword;
    private String wrongPassword;
    public static final String GUEST_NAME_1 = "Major Gowen";
    public static final String GUEST_NAME_2 = "Miss Tibbs";
    public static final String GUEST_NAME_3 = "Miss Gatsby";

    public static final int NUMBER_OF_NIGHTS = 5;

    /**
     * Sets the instance variable <tt>hotel</tt> to a well-defined initial value.
     * All test methods should be preceded by a call to this method.
     */
    @BeforeEach
    public void setUp() {
        hotel = new Hotel("Fawlty Towers");

        // initialisation of password-variable
        correctPassword = new Password().getInitPass();
        wrongPassword = correctPassword + "_invalid";
    }

    /**
     * checkIn First room should be a PricedRoom
     */
    @Test
    public void testCheckIn() {
        Room room = hotel.checkIn(GUEST_NAME_1);
        assertTrue(room instanceof PricedRoom,
    			"room should be an instance of PricedRoom");
        Safe safe = room.getSafe();
    	assertTrue(safe instanceof PricedSafe,
    			"safe should be an instance of PricedSafe");
    }

    /**
     * checkIn must, as long as rooms are available, return a room occupied by the specified guest.
     * When the hotel is full, checkIn must return null.
     */
    @Test
    public void testCheckInWithCorrectPassword() {
        Room room1 = hotel.checkIn(GUEST_NAME_1);
        assertEquals(GUEST_NAME_1, room1.getGuest().getName(), "Correct 1st guest check in");

        Room room2 = hotel.checkIn(GUEST_NAME_2);
        assertEquals(GUEST_NAME_2, room2.getGuest().getName(), "Correct 2nd guest check in");

        Room noRoom = hotel.checkIn(GUEST_NAME_3);
        assertNull(noRoom, "No check in if hotel is full");
    }

    /**
     * If the specified guest is checked in, he must be checked out, i.e., afterwards, he must not have a room anymore,
     * and his room must now be empty.
     */
    @Test
    public void testCheckoutOccupiedRoom() {
        Room room = hotel.checkIn(GUEST_NAME_1);
        Guest guest = room.getGuest();
        assertTrue(room instanceof PricedRoom,
    			"room should be an instance of PricedRoom");
        Safe safe = room.getSafe();
    	assertTrue(safe instanceof PricedSafe,
    			"safe should be an instance of PricedSafe");
        hotel.checkOut(GUEST_NAME_1);
        assertNull(guest.getRoom(), "Guest has no room");
        assertNull(room.getGuest(), "Room has no guest");
        assertFalse(safe.isActive(), "Safe is inactive");
    }

    @Test
    public void testCheckoutEmptyRoom() {
        hotel.checkOut(GUEST_NAME_1);
        // nothing to be checked here, but no exception should occur.
    }

    /**
     * If there is a free room, getFreeRoom must return a room without guest.
     */
    @Test
    public void testGetFreeRoomFromNotFullHotel() {
        Room room = hotel.getFreeRoom();
        assertNull(room.getGuest(), "A room is free");
        hotel.checkIn(GUEST_NAME_1);
        Room freeRoom = hotel.getFreeRoom();
        assertNotNull(freeRoom, "Another room is free");
        assertNotEquals(room, freeRoom, "Another room is free");
    }

    /**
     * If there is no free room, getFreeRoom must return null.
     */
    @Test
    public void testGetFreeRoomFromFullHotel() {
        hotel.checkIn(GUEST_NAME_1);
        hotel.checkIn(GUEST_NAME_2);

        Room noRoom = hotel.getFreeRoom();
        assertNull(noRoom, "No room available in a full hotel");
    }

    /**
     * getRoom must not return any room, if the guest is not checked in
     */
    @Test
    public void testGetRoomBeforeCheckIn() {
        Room room = hotel.getRoom(GUEST_NAME_1);
        assertNull(room, "Guest 1 not checked in");
    }

    /**
     * If the guest is checked in, the returned room must be occupied by the specified guest.
     */
    @Test
    public void testGetRoomAfterCheckIn() {
        hotel.checkIn(GUEST_NAME_1);
        Room room = hotel.getRoom(GUEST_NAME_1);
        assertEquals(GUEST_NAME_1, room.getGuest().getName(), "Guest 1 checked in");
    }

    /**
     * A password object must be returned.
     */
    @Test
    public void testGetPassword() {
        Room room = hotel.checkIn(GUEST_NAME_1);
        assertTrue(room instanceof PricedRoom,
    			"room should be an instance of PricedRoom");
        Safe safe = room.getSafe();
    	assertTrue(safe instanceof PricedSafe,
    			"safe should be an instance of PricedSafe");

        Password password = ((PricedSafe) safe).getPassword();
        assertNotNull(password, "Returned password is not null");
    }

    /**
     * The Bill should be correctly calculated
     */
    @Test
    public void testGetBill() {
        Room room = hotel.checkIn(GUEST_NAME_1);
        assertTrue(room instanceof PricedRoom,
    			"room should be an instance of PricedRoom");
        StringPrinter printer = new StringPrinter();
        Bill bill = hotel.getBill(GUEST_NAME_1, NUMBER_OF_NIGHTS, printer);
        double nightsTotal = NUMBER_OF_NIGHTS * Hotel.ROOM_PRICE;
        assertEquals(nightsTotal, bill.getSum(), 0.01, "Bill sum should contain only room costs.");
		assertTrue(printer.getResult().contains(String.format("%.2f", nightsTotal)));

        // Now check sum again when safe is actually active
        assertTrue(room.getSafe() instanceof PricedSafe);
        PricedSafe safe = (PricedSafe) room.getSafe();
        assertFalse(safe.isActive(), "Safe should be deactivated after initialisation.");
        safe.activate(correctPassword);
        assertTrue(safe.isActive(), "Safe should be activated after activation with password.");
        assertEquals(nightsTotal + Hotel.SAFE_PRICE,
                hotel.getBill(GUEST_NAME_1, NUMBER_OF_NIGHTS, printer).getSum());
    }

    /**
     * ToString is difficult to test fully because there is no restriction on the format of the returned String.
     * At least it can be tested that a String is returned and that it contains the name of a checked in guest.
     */
    @Test
    public void testToString() {
        hotel.checkIn(GUEST_NAME_1);
        assertTrue(hotel.toString().contains(GUEST_NAME_1));
    }
}
