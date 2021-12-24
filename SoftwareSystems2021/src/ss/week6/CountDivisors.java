package ss.week6;

public class CountDivisors implements Runnable{
    private int number;
    private int divisors;

    /**
     * constructor
     * @param number the integer of which to compute the number of divisors
     */
    public CountDivisors(int number) {
        this.number = number;
    }

    /**
     * computes the number of divisors for a single
     * @return
     */
    public void countDivisors() {
        for (int i = 1; i <= this.number; i++) {
            if (this.number % i == 0) {
                this.divisors++;
            }
        }
    }

    /**
     * get the number of divisors
     * @return the number of distinct divisors
     */
    public int getDivisors() {
        return this.divisors;
    }

    /**
     * run method
     */
    @Override
    public void run() {
        countDivisors();
    }
}
