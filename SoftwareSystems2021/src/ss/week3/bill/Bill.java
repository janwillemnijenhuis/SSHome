package ss.week3.bill;

public class Bill {
    private final Printer printer;
    private double sum;

    public Bill(Printer printer) {
        this.printer = printer;
        this.sum = 0.0;
    }

    public interface Item {
        double getPrice();
        String toString();
    }

    public void addItem(Bill.Item item) {
        this.sum += item.getPrice();
        this.printer.printLine(item.toString(), item.getPrice());
    }

    public void close() {
        this.printer.printLine("The total is: ", this.sum);
    }

    public double getSum() {
        return this.sum;
    }
}