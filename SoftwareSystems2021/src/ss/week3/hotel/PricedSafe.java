package ss.week3.hotel;

import ss.week3.bill.Bill;
import ss.week3.password.Password;

public class PricedSafe extends Safe implements Bill.Item{
    private double price;
    private Password password;
    /**
     * Constructor of priced safe
     */
    public PricedSafe(double price) {
        this.price = price;
        this.password = new Password();
    }

    /**
     * activates the safe if the pwd is correct
     * @param password the pwd
     */
    public void activate(String password) {
        if(this.password.testWord(password)) {
            super.activate();
        }
    }

    /**
     * overrides parent method, gives warning since no pwd provided
     */
    @Override
    public void activate() {
        System.out.println("Warning, safe not activated!");
    }

    /**
     * closes and deactivates the safe
     */
    public void deactivate() {
        super.deactivate();
    }

    /**
     * Opens the safe if pwd is correct and safe is activated
     * @param password the pwd
     */
    public void open(String password) {
        if(this.password.testWord(password) && super.isActive()) {
            super.open();
        }
    }

    /**
     * does not change the state of the safe
     */
    @Override
    public void open() { }

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