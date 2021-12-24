package ss.week6.threads;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedCell implements IntCell {

    private int value;
    private Lock lock = new ReentrantLock();
    private Condition Full = lock.newCondition();
    private Condition Empty = lock.newCondition();
    private boolean containsValue = false;
    public ArrayList<Integer> values = new ArrayList<>();

    @Override
    public void setValue(int val) {
        lock.lock();
        try {
            while (containsValue) {
                Empty.await();
            }
            this.value = val;
            this.containsValue = true;
            values.add(val);
            Full.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getValue() {
        lock.lock();
        try {
            while (!containsValue) {
                Full.await();
            }
            this.containsValue = false;
            values.add(-this.value);
            System.out.println(values);
            Empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return this.value;
    }
}
