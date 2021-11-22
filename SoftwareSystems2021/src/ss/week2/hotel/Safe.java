package ss.week2.hotel;

public class Safe {
    private static boolean activated;
    private static boolean opened;

    public Safe() {
        this.activated = false;
        this.opened = false;
    }

    public void activate() {
        this.activated = true;
    }

    public void deactivate() {
        this.close();
        this.activated = false;
    }

    public void open() {
        if(this.isActive()) {
            this.opened = true;
        }
    }

    public void close() {
        this.opened = false;
    }

    public boolean isActive() {
        if(this.activated == true) {
            return true;
        }
        return false;
    }

    public boolean isOpen() {
        if(this.opened == true) {
            return true;
        }
        return false;
    }
}