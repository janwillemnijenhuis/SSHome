package ss.week6.threads;

import java.util.ArrayList;

public class SynchronizedIntCell implements IntCell {
    private boolean containsValue = false;
    public ArrayList<Integer> values = new ArrayList<>();
    private int value = 0;

    @Override
    synchronized public void setValue(int val) {
        while (containsValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
        values.add(val);
        this.value = val;
        this.containsValue = true;
    }

    @Override
    public synchronized int getValue() {
        while (!containsValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
        values.add(-this.value);
        this.containsValue = false;
        System.out.println(values);
        return this.value;
    }
}
