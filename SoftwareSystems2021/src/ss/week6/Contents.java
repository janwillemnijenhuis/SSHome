package ss.week6;

public class Contents {
    private int contents = 0;

    public void add(int amount) {
        contents = contents + amount;
    }

    public int get() {
        return contents;
    }

    public static void main(String[] args) {
        Contents cell = new Contents();
        Thread a1 = new Thread(new Adder(cell, 1));
        Thread a2 = new Thread(new Adder(cell, 2));
        // if we use start we create new thread and then execute run on this thread. if we use run we execute the code on the current thread, so a1 => a2. the results will be the same since we use join
        a1.start();
        a2.start();
        // commenting the lines from the try-catch results in two thread not waiting for each other and performing 0+2 and 0+1
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cell.get());
    }
}

class Adder implements Runnable {
    private Contents cell;
    private int amount;

    public Adder(Contents cellArg, int amountArg) {
        this.cell = cellArg;
        this.amount = amountArg;
    }
    public void run() {
        cell.add(amount);
    }
}
