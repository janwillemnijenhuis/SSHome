package ss.week6;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CountDivisorsMutliThread extends CountDivisorsSingleThread{
    private int numThreads;
    private ConcurrentLinkedQueue<CountDivisors> clq;
    private boolean running = false;

    /**
     * constructor
     *
     * @param min smallest int to compute divisors for
     * @param max largest int ...
     */
    public CountDivisorsMutliThread(int min, int max, int numThreads) {
        super(min, max);
        this.numThreads = numThreads;
    }

    @Override
    public void loopNumbers() {
        long start = System.currentTimeMillis();
        this.clq = new ConcurrentLinkedQueue<>();
        for (int i = super.getMin(); i <= super.getMax(); i++) {
            CountDivisors cd = new CountDivisors(i);
            this.clq.add(cd);
        }
        long end = System.currentTimeMillis();
        elapsedTime(start, end);
    }

    public ConcurrentLinkedQueue<CountDivisors> getQueue() {
        return this.clq;
    }
}

