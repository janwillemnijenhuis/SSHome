package ss.week3.bill;

public class SysoutPrinter implements Printer {
    public SysoutPrinter(){}

    public static void main(String[] args) {
        Printer p = new SysoutPrinter();
        p.printLine("test", 3);
    }

    public void printLine(String text, double price) {
        System.out.print(format(text, price));
    }

    @Override
    public void addLine(String text) {
        System.out.print(text + "\n");
    }
}