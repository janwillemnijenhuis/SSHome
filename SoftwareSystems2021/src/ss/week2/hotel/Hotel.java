package ss.week2.hotel;

import java.util.ArrayList;

public class Hotel {
    public int MAXSIZE = 2; // default hotel size
    public final String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Guest> guests = new ArrayList<>();

    public Hotel(String name) {
        this.name = name;
    }

    public Room checkIn(String name) {
        if (getRoom(name) == null && getFreeRoom() != null) {
            Room room = this.getFreeRoom();
            Guest guest = new Guest(name);
            guest.checkin(room);
            guests.add(guest);
            rooms.add(room);
            System.out.format("Successfully checked in Guest %s.%n", guest.getName());
            return room;
        } else {
            System.out.format("Could not check Guest %s in. Either the hotel is full or the Guest already exists.%n", name);
            return null;
        }
    }

    public void checkOut(String name) {
        boolean checkedOut = false;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.guests.get(i).getName().equals(name)) {
                guests.get(i).checkout();
                rooms.remove(i);
                guests.remove(i);
                System.out.format("Successfully checked out Guest %s.%n", name);
                checkedOut = true;
            }
        }
        if(!checkedOut) {
            System.out.format("Guest %s could not be checked out, did not stay at the hotel.%n", name);
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
            if(s >= 1) {
                ArrayList<Integer> roomNumbers = new ArrayList<Integer>();
                for (int i = 0; i < rooms.size(); i++) {
                    roomNumbers.add(rooms.get(i).getNumber());
                }
                for (int j = 0; j < MAXSIZE; j++) {
                    if (!roomNumbers.contains(j)) {
                        return new Room(j);
                    }
                }
            }
            else {
                return new Room(s);
            }
        }
        return null;
    }

    public String toString() {
        String output = String.format("The Hotel %s is composed of the following rooms and guests:%n", this.name);
        int nOcc = this.rooms.size();
        for(int i = 0; i < nOcc; i++) {
            Room room = this.rooms.get(i);
            Guest guest = this.rooms.get(i).getGuest();
            if (room.safe.isActive()) {
                output += String.format("%s is occupied by guest %s. The safe is activated.%n", room.toString(), guest.toString());
            } else {
                output += String.format("%s is occupied by guest %s. The safe is deactivated.%n", room.toString(), guest.toString());
            }
        }
        return output;
    }
}