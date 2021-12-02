package ss.week2;
import java.lang.Math;

public class PairOfDice {
    private int die1;
    private int die2;
    private int rolls;

    public PairOfDice() {
        this.rolls = 0;
    }

    // rolls the dice after it has been rolled for a number of times, and shows two values.
    public PairOfDice(int val1, int val2, int rolls) {
        this.die1 = val1;
        this.die2 = val2;
        this.rolls = rolls;
    }

    public void roll() {
        this.die1 = (int) (Math.random() * 6) + 1;
        this.die2 = (int) (Math.random() * 6) + 1;
        rolls += 1;
    }

    public int getSum() {
        return this.die1 + this.die2;
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }

    public int getRolls() {
        return rolls;
    }

    public String toString() {
        return String.format("The dice have been rolled %d times.%n", getRolls())
                + String.format("Die 1 shows: %d.%n", getDie1())
                + String.format("Die 2 shows: %d.%n", getDie2());
    }
}

