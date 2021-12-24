package ss.week6;

public class CountDivisorsSingleThread {
    private int min;
    private int max;
    private double time;
    private int number;
    private int divisors;

    /**
     * constructor
     * @param min smallest int to compute divisors for
     * @param max largest int ...
     */
    public CountDivisorsSingleThread(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * loops over min to max and updates the largest number of divisors and the corresponding integer value
     */
    public void loopNumbers() {
        long start = System.currentTimeMillis();
        for (int i = this.min; i <= this.max; i++) {
            CountDivisors cd = new CountDivisors(i);
            cd.countDivisors();
            updateMostDivisors(cd.getDivisors(), i);
        }
        long end = System.currentTimeMillis();
        elapsedTime(start, end);
    }

    /**
     * computes and updates the elapsed time for all iterations
     * @param start startime in ms
     * @param end endtime in ms
     */
    public void elapsedTime(long start, long end) {
        this.time = (end - start) / 1000.0;
    }

    /**
     * update the integer with the largest number of divisors if its larger than the current number of divisors
     * @param number the integer
     */
    public void updateInteger(int number) {
        this.number = number;
    }

    /**
     * update the largest number of divisors if its larger than the current number of divisors
     * @param divisors the number of divisors
     * @param number the number corresponding to this
     */
    public void updateMostDivisors(int divisors, int number) {
        if (this.divisors < divisors) {
            this.divisors = divisors;
            updateInteger(number);
        }
    }

    /**
     * getter for the elapsed time
     * @return the time elapsed
     */
    public double getTime() {
        return this.time;
    }

    /**
     * getter for the integer with the largest number of divisors
     * @return the largest number of divisors
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * getter for the largest number of divisors
     * @return the largest of number divisors
     */
    public int getDivisors() {
        return this.divisors;
    }

    /**
     * getter for the min
     * @return
     */
    public int getMin() {
        return this.min;
    }

    /**
     * getter for the max
     * @return
     */
    public int getMax() {
        return this.max;
    }

}
