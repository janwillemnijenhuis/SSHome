package ss.week1;
/*
        Write a program that helps the user count his change. The program should ask how many (solution)
        quarters (25) the user has, then how many dimes (10), then how many nickels (5), then how many
        pennies (1). Then the program should tell the user how much money he has, expressed in
        dollars.
*/

import ss.utils.TextIO;

public class ChangeCounter {

    public static void main(String[] args) {

        boolean exit = false;
        int quarters = 0;
        int dimes = 0;
        int nickles = 0;
        int pennies = 0;

        while (!exit) {

            System.out.println("Number of coins, quarters, dimes, nickles, pennies, respectively with spaces in between: ");
            String[] input = TextIO.getlnString().split(" ");
            if (input.length == 4) {
                quarters = Integer.parseInt(input[0]);
                dimes = Integer.parseInt(input[1]);
                nickles = Integer.parseInt(input[2]);
                pennies = Integer.parseInt(input[3]);
                exit = true;
            }
            else {
                System.out.println("Error, please provide a valid input!");
            }
        }

        double dollars = 0.25 * quarters + 0.10 * dimes + 0.05 * nickles + 0.01 * pennies;
        System.out.println("The amount of dollars is: " + dollars);

    }
}