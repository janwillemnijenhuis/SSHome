package ss.week2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.week2.hotel.Safe;
import ss.week2.hotel.Guest;
import ss.week2.hotel.Room;

public class RoomTest {
    private Guest guest;
    private Room room;

    @BeforeEach
    public void setUp() {
        guest = new Guest("Jip");
        // TODO: initialise the variable room
        room = new Room(101);
    }

    @Test
    public void testSetUp() {
        assertEquals(101, room.getNumber());
    }

    @Test
    public void testSetGuest() {
        room.setGuest(guest);
        assertEquals(guest, room.getGuest());
    }

    @Test
    public void testSafe() {
        Safe safe = new Safe();
        assertEquals(room.safe, room.getSafe());
    }
}
