package ss.week3.bill;

public interface Printer {
    void printLine(String text, double price);
    void addLine(String text);
    default String format(String text, double price) {
        return String.format("%-15s      %15.2f\n", text, price);
    }
}
