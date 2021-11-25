package ss.week2;

public class PlayDice {
    public static void main(String[] args) {
        PairOfDice pair = new PairOfDice();
        PairOfDice pair2 = new PairOfDice();
        pair2.roll();
        System.out.println(pair2.toString());
        pair.roll();
        System.out.println(pair.toString());
        pair.roll();
        System.out.println(pair.toString());
        pair.roll();
        System.out.println(pair2.toString());
    }
}