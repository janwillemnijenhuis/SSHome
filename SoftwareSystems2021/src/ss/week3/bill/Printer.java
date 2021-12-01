package ss.week3.bill;

interface Printer {
    void printLine(String text, double price);
    default String format(String text, double price) {
        return String.format("%-15s      %15.2f\n", text, price);
    }
}
