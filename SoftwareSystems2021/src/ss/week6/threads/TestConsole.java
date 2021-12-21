package ss.week6.threads;

public class TestConsole implements Runnable{
    @Override
    public void run() {
        sum();
    }

    private void sum() {
        int num1 = Console.readInt(Thread.currentThread().getName() + ": get number 1? ");
        int num2 = Console.readInt("Thread A: get number 2? ");
        int sum = num1 + num2;
        Console.println("Thread A: " + num1 + " + " + num2 + " = " + sum);
    }

    public static void main(String[] args) {
        new Thread(new TestConsole(), "Thread A").start();
        new Thread(new TestConsole(), "Thread A").start();
        /// Exercise 6.9 ///
        // the behaviour is problematic as the threads start simultaneously and you don't know to which testconsole you're replying
    }
}
