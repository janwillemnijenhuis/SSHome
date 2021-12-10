package ss.week3;

public class OvertimeEmployee extends Employee {
    public OvertimeEmployee(int hours) {
        super(hours, new PayCalculator() {
            @Override
            public int pay(int hours, PayCalculator payCalculator) {
                int overtime = Math.max(hours - 160, 0);
                return hours * 20 + (int) (0.5 * 20 * overtime);
            }
        });
    }
}