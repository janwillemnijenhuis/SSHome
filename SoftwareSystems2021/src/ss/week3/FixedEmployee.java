package ss.week3;

public class FixedEmployee extends Employee {
    public FixedEmployee(int hours) {
        super(hours, new PayCalculator() {
            @Override
            public int pay(int hours, PayCalculator payCalculator) {
                return hours * 20;
            }
        });
    }
}