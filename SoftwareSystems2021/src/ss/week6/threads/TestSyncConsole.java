package ss.week6.threads;

public class TestSyncConsole implements Runnable {
    @Override
    public void run() {
        sum();
    }

    private synchronized void sum() {
        int num1 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 1? ");
        int num2 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 2? ");
        int sum = num1 + num2;
        SyncConsole.println(Thread.currentThread().getName() + ": " + num1 + " + " + num2 + " = " + sum);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestSyncConsole(), "Thread A");
        Thread t2 = new Thread(new TestSyncConsole(), "Thread B");
        t1.start();
        t1.join();
        t2.start();
        /// Excercise 6.11 ///
        // they are not processed in the same order, first B1, A1, A2, B2
        // Exercise 6.12 there is no difference. synchronized is used s.t. only a single thread can access a method at the same time.
        // both can access run() at the same time. then only the first can access sum(), but when he does he goes into readint().
        // then the second thread can access sum() now as the first thread has moved into readint(). Now first we ask for number 1 of A (or B)
        // and then for number 1 of the second, since readint() is synchronized. But the order in which number 2 is processed can be different or the same,
        // depending on which thread is done last, as this thread is the first to go back to sum(), since this is synchronized. Hence you will get the order
        // A1, B1, B2, A2 and then both are printed.
        // using join thread B has to wait for A
    }

}
