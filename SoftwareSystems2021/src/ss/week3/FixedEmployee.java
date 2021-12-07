package ss.week3;

public class FixedEmployee extends Employee {

    public FixedEmployee(int hours, int rate) {
        super();

    }

    class FixedPayCalculator implements PayCalculator {
        private int rate;
        private int hours;

        public FixedPayCalculator(int rate, int hours) {
            this.rate = rate;
        }

        @Override
        public int pay() {
            return this.hours * this.rate;
        }
    }
}