package ss.week3.hotel;

import ss.week3.bill.Bill;

public class PricedRoom extends Room implements Bill.Item {
    private double price;
    private PricedSafe safe;
    private int number;
    private double safePrice;

    public static void main(String[] args) {
        PricedRoom p = new PricedRoom(0, 100.00, 25.00);
        System.out.println(p);
        System.out.println(p.getSafe());
    }

    public PricedRoom(int number, double price, double safePrice) {
        super(number, new PricedSafe(safePrice));
        this.safe = (PricedSafe) super.getSafe();
        this.price = price;
        this.number = super.getNumber();
        this.safePrice = safePrice;
    }

    // i think i cheated a bit here
    @Override
    public Safe getSafe() {
        return this.safe;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return "Room " + super.getNumber() + " (" + this.price + "/night): ";
    }
}