package ss.week3;

public class Employee {
    private int hours;
    private PayCalculator payCalculator;

    public Employee(int hours, PayCalculator payCalculator) {
        this.hours = hours;
        this.payCalculator = payCalculator;
    }

    public Employee() {

    }

    public int hours() {
        return this.hours;
    }
}