package ss.week3.bill;

public class StringPrinter implements Printer {
    private String totalString = "";

    public StringPrinter(){}

    public void printLine(String text, double price) {
        this.totalString += format(text, price);
    }

    public String getResult() {
        return this.totalString;
    }
}