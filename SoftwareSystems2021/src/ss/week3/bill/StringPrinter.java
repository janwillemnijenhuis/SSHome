package ss.week3.bill;

public class StringPrinter implements Printer {
    private String totalString = "";

    public StringPrinter(){}

    public void printLine(String text, double price) {
        this.totalString += format(text, price);
    }

    public void addLine(String text) {this.totalString += text;}

    public String getResult() {
        String total = this.totalString;
        this.totalString = "";
        return total;
    }
}