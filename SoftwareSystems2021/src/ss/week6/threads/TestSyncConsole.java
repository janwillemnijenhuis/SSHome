package ss.week6.threads;

import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole implements Runnable {
    ReentrantLock re;

    public TestSyncConsole(ReentrantLock re) {
        this.re = re;
    }

    @Override
    public void run() {
        sum();
    }

    private void sum() {
        try {
            synchronized (System.in) {
//            re.lock();
                int num1 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 1? ");
                int num2 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 2? ");
                int sum = num1 + num2;
                SyncConsole.println(Thread.currentThread().getName() + ": " + num1 + " + " + num2 + " = " + sum);
            }
        } finally {
//            re.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock re = new ReentrantLock();
        Thread t1 = new Thread(new TestSyncConsole(re), "Thread A");
        Thread t2 = new Thread(new TestSyncConsole(re), "Thread B");
        t1.start();
       // t1.join();
        t2.start();
        /// Exercise 6.11 ///
        // they are not processed in the same order, first B1, A1, A2, B2
        /// Exercise 6.12 ///
        // there is no difference. synchronized is used s.t. only a single thread can access a method at the same time.
        // but we create two new instances of testsyncconsole. therefore they can both access their own methods
        // if we would create one instance it would work, or we can use join
        /// Exercise 6.14 ///
        // 1. What does it mean for a lock to be reentrant? the thread holding the lock can re-enter into the lock and hence create a queue before it is unlocked. Every time the thread unlocks the hold count is decremented by one
        // 2. Is this behaviour different from the synchronized statement? yes if the thread occupying a method with the syncronized statement leaves the method any thread can take the lock
        // 3. What would be advantages of using a ReentrantLock? you have more flexibility in when you want a thread to go first
        // 4. And what would be disadvantages? you would have to unlock each lock and cannot forget this
    }
}
