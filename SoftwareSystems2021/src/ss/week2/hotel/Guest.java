package ss.week2.hotel;
/**
 * Guest class which enables user to connect a room to a guest, and vice versa
 * @author Jan Willem Nijenhuis
 */
public class Guest {
    private Room room;
    private final String name;

    /**
     * initialize guest
     * @requires name instanceof String;
    */
    public Guest(String name) {
        this.name = name;
        this.room = null;
    }

    /**
     * get the room that is assigned to the guest
     *
     * @ensures this.room == null ==> \result == null;
     * @ensures this.room != null ==> \result == this.room;
    */
    public Room getRoom() {
        if(this.room == null){
            return null;
        }
        else {
            return this.room;
        }
    }

    /**
     * checks guest in, if the room is available;
     *
     * @param room the room to check into;
     * @requires room instanceof Room;
     * @ensures this.getRoom() == null && room.getGuest() == null ==> \result == true;
     * @ensures this.getRoom() != null || room.getGuest() != null ==> \result == false;
     * @return boolean for correct checkin
    */
    public boolean checkin(Room room) {
        if(this.getRoom() == null && room.getGuest() == null) {
            this.room = room;
            room.setGuest(this);
            return true;
        }
        return false;
    }

    /**
     * checks guest out of room, if he is currently in one
     *
     * @ensures this.room == null ==> \result == false;
     * @ensures this.room != null ==> \result == true;
     * @return boolean for correct checkout
    */
    public boolean checkout() {
        if(this.room == null) {
            return false;
        }
        else {
            room.setGuest(null);
            this.room = null;
            return true;
        }
    }

    /**
     * returns name of guest
     *
     * @requires this.name instanceof String;
     * @return String name of guest;
    */
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Guest " + this.name;
    }
}