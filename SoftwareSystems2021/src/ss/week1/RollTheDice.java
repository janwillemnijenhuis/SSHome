package ss.week1;
/**
 * RollTheDice class
 */
public class RollTheDice {

    public static void main(String[] args) {

        int firstDie = (int) (Math.random() * 6) + 1;
        int secondDie = (int) (Math.random() * 6) + 1;
        int sum = firstDie + secondDie;

        System.out.format("The first die comes up %d%n", firstDie);
        System.out.format("The second die comes up %d%n", secondDie);
        System.out.format("Your final result is %d%n", sum);
    }


}