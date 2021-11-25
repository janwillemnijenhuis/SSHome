package ss.week2.hotel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Hotel {
    private static final int MAXSIZE = 2;
    private final String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Guest> guests = new ArrayList<>();
    public static final String GUEST_NAME_1 = "Major Gowen";

    public static void main(String[] args) {
        Hotel hotel = new Hotel("test hotel");
        Room room1 = hotel.checkIn(GUEST_NAME_1);
        Guest guest1 = room1.getGuest();
        System.out.println(guest1.getName());
    }

    public Hotel(String name) {
        this.name = name;
    }

    public Room checkIn(String name) {
        if (getRoom(name) == null && getFreeRoom() != null) {
            Room room = this.getFreeRoom();
            Guest guest = new Guest(name);
            guest.checkin(room);
            room.setGuest(guest);
            guests.add(guest);
            rooms.add(room);
            return room;
        } else {
            return null;
        }
    }

    public void checkOut(String name) {
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.guests.get(i).getName().equals(name)) {
                guests.get(i).checkout();
            }
        }
    }

    public Room getRoom(String name) {
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.guests.get(i).getName().equals(name)) {
                return this.guests.get(i).getRoom();
            }
        }
        return null;
    }

    public Room getFreeRoom() {
        int s = this.rooms.size();
        if (s < MAXSIZE) {
            return new Room(s);
        }
        return null;
    }

    public String toString() {
        String output = String.format("The Hotel %s is composed of the following rooms and guests:%n", this.name);
        int noOcc = this.rooms.size();
        for(int i = 0; i < noOcc; i++) {
            Room room = this.rooms.get(i);
            Guest guest = this.rooms.get(i).getGuest();
            if (room.safe.isActive()) {
                output += String.format("%s is occupied by guest %s. The safe is activated.%n", room.toString(), guest.toString());
            } else {
                output += String.format("%s is occupied by guest %s. The safe is deactivated.%n", room.toString(), guest.toString());
            }
        }
        if (noOcc < MAXSIZE) {
            for (int j = 0; j < MAXSIZE - noOcc; j++) {
                output += String.format("Room %d is empty.%n", MAXSIZE - j - 1);
            }
        }
        return output;
    }
}