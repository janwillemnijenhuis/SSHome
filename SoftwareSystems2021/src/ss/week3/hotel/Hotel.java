package ss.week3.hotel;

import ss.week3.bill.Bill;
import ss.week3.bill.Printer;
import ss.week3.bill.StringPrinter;
import ss.week3.hotel.*;

import java.util.ArrayList;

public class Hotel {
    public int MAXSIZE = 2; // default hotel size
    public final String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Guest> guests = new ArrayList<>();
    public static double ROOM_PRICE = 100.00;
    public static double SAFE_PRICE = 25.00;
    private PricedRoom pricedRoom;

    public Hotel(String name) {
        // ik betwijfel of ik dit op de juiste manier doe
        this.name = name;
        this.pricedRoom = new PricedRoom(1, this.ROOM_PRICE, this.SAFE_PRICE);
    }

    public Bill getBill(String name, int nights, Printer printer) {
        class Item implements Bill.Item {
            private double price;
            private String name;
            public Item(String name, double price) {
                this.price = price;
                this.name = name;
            }
            @Override
            public double getPrice() {
                return price;
            }

            public String toString() {
                return this.name;
            }
        }

        Bill b = new Bill(printer);
        b.writeLine(name + " stayed for " + nights + " nights.\n");
        if(this.getRoom(name) instanceof PricedRoom) {
            Item night = new Item(this.getRoom(name).toString(), this.ROOM_PRICE);
            for(int i = 0; i < nights; i++) {
                b.addItem(night);
            }
        } else {
            Item night = new Item(this.getRoom(name).toString(), 0);
            for(int i = 0; i < nights; i++) {
                b.addItem(night);
            }
        }
        if(this.getRoom(name).getSafe() instanceof PricedSafe && this.getRoom(name).getSafe().isActive()) {
            Item safe = new Item("safe", this.SAFE_PRICE);
            b.writeLine("The safe was activated.\n");
            b.addItem(safe);
        }
        else if(this.getRoom(name).getSafe().isActive()) {
            Item safe = new Item("safe", 0);
            b.writeLine("The safe was activated.\n");
            b.addItem(safe);
        }
        b.close();
        return b;
    }

    public Room checkIn(String name) {
        if (getRoom(name) == null && getFreeRoom() != null && this.pricedRoom.getGuest() == null) {
            Guest guest = new Guest(name);
            guest.checkin(this.pricedRoom);
            guests.add(guest);
            rooms.add(this.pricedRoom);
            System.out.format("Successfully checked in %s in %s.%n", this.getRoom(name).getGuest(), this.getRoom(name));
            return this.pricedRoom;
        } else if (getRoom(name) == null && getFreeRoom() != null) {
            Room room = this.getFreeRoom();
            Guest guest = new Guest(name);
            guest.checkin(room);
            guests.add(guest);
            rooms.add(room);
            System.out.format("Successfully checked in %s in %s.%n", this.getRoom(name).getGuest(), this.getRoom(name));
            return room;
        }
        else {
            System.out.format("Could not check Guest %s in. Either the hotel is full or the Guest already exists.%n", name);
            return null;
        }
    }

    public void checkOut(String name) {
        boolean checkedOut = false;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.guests.get(i).getName().equals(name)) {
                Guest guest = this.getRoom(name).getGuest();
                Room room = this.getRoom(name);
                guests.get(i).checkout();
                rooms.remove(i);
                guests.remove(i);
                System.out.format("Successfully checked out %s from %s.%n", guest.toString(), room.toString());
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
                output += String.format("%s is occupied by %s. The safe is activated.%n", room.toString(), guest.toString());
            } else {
                output += String.format("%s is occupied by %s. The safe is deactivated.%n", room.toString(), guest.toString());
            }
        }
        return output;
    }
}