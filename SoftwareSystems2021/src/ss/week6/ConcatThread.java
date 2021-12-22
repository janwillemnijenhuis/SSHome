package ss.week6;

import java.util.concurrent.TimeUnit;

public class ConcatThread extends Thread {
    private static String text = ""; // global variable
    private String toe;

    public ConcatThread(String toeArg) {
        this.toe = toeArg;
    }

    public void getText() {
        System.out.println(this.text);
    }

    synchronized public void run() {
        synchronized (text) {
            text = text.concat(toe);
        }
    }

    public static void main (String[] args) throws InterruptedException {
        (new ConcatThread("one;")).start();
        (new ConcatThread("two;")).start();
        TimeUnit.SECONDS.sleep(1);
        ConcatThread c1 = new ConcatThread("");
        c1.getText();
    }
}

