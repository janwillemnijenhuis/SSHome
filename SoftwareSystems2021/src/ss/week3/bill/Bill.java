package ss.week3.bill;

public class Bill {
    public final Printer printer;
    private double sum;

    public static void main(String[] args) {
        StringPrinter p = new StringPrinter();
        Bill b = new Bill(p);
        Night n = new Night(5);
        b.addItem(n);
        System.out.println(p.getResult());
    }

    public Bill(Printer printer) {
        this.printer = printer;
        this.sum = 0.0;
    }

    public interface Item {
        double getPrice();
        String toString();
    }

    public static class Night implements Bill.Item {
        public double nights;

        public Night(int nights) {
            this.nights = nights;
        }

        @Override
        public double getPrice() {
            return this.nights;
        }

        public String toString() {
            return "The number of nights is:";
        }
    }

    public void addItem(Bill.Item item) {
        this.sum += item.getPrice();
        this.printer.printLine(item.toString(), item.getPrice());
    }

    public void writeLine(String text) {
        this.printer.addLine(text);
    }

    public void close() {
        this.printer.printLine("The total is: ", this.sum);
    }

    public double getSum() {
        return this.sum;
    }
}