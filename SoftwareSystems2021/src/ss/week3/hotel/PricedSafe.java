package ss.week3.hotel;

import ss.week3.bill.Bill;
import ss.week3.password.Password;

public class PricedSafe extends Safe implements Bill.Item{
    private double price;
    private Password password;

    /**
     * Main method
     */
    public static void main(String[] args) {
        PricedSafe p = new PricedSafe(5.00);
        String WRONG_PASSWORD = "wrongpassword123";
        p.activate(WRONG_PASSWORD);
    }

    /**
     * Constructor of priced safe
     */
    public PricedSafe(double price) {
        this.price = price;
        this.password = new Password();
    }

    /**
     * activates the safe if the pwd is correct
     * @requires password to be correct
     * @param password the pwd
     */
    public void activate(String password) {
        //assert this.password.testWord(password);
        if(this.password.testWord(password)) {
            super.activate();
        }
    }

    /**
     * overrides parent method, gives warning since no pwd provided
     */
    @Override
    public void activate() {
        System.err.print("Warning, safe not activated!\n");
    }

    /**
     * closes and deactivates the safe
     */
    public void deactivate() {
        super.deactivate();
    }

    /**
     * Opens the safe if pwd is correct and safe is activated
     * @requires password correct
     * @requires safe is active
     * @param password the pwd
     */
    public void open(String password) {
        //assert this.password.testWord(password) == true;
        //assert super.isActive() == true;
        if(this.password.testWord(password) && super.isActive()) {
            super.open();
        }
    }

    /**
     * does not change the state of the safe
     */
    @Override
    public void open() { System.err.print("State of safe not changed\n");}

    /**
     * closes the safe
     */
    public void close() {
        super.close();
    }

    /**
     * getter for the password
     * @return the pwd
     */
    public Password getPassword() {
        return this.password;
    }

    public double getPrice() {
        return this.price;
    }

    /**
     * prints the string formatted object of the priced safe
     * @return string
     */
    public String toString() {
        return String.format("The priced safe costs: %s", this.price);
    }
}