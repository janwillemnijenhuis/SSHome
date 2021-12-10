package ss.week3;

public class Employee {
    private int hours;
    private PayCalculator payCalculator;

    public Employee(int hours, PayCalculator payCalculator) {
        this.hours = hours;
        this.payCalculator = payCalculator;
    }

    public int hours() {
        return this.hours;
    }

    public int pay() {
        return this.payCalculator.pay(hours(), payCalculator);
    }
}