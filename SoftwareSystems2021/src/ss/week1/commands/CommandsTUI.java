package ss.week1.commands;

import ss.utils.TextIO;
import ss.week1.Fibonacci;
import ss.week1.BabylonianAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandsTUI {
    /*
    This class implements a TUI to ask user for input, and then performs actions

    The possible actions are: Fibonacci, Babylonian Algorithm, Max number of a list of numbers
     */
    public static void main(String[] args) {
        // print the start menu
        printMenu();

        boolean exit = false;

        while(!exit) {

            System.out.print("Command: ");
            String[] input = TextIO.getlnString().split(" ");
            String command = input[0];

            if(input.length >= 1) { // check if any input arguments are given
                switch (command) {
                    case "FIB":
                        if(input.length == 2 && isNumeric(input[1])) {
                            int n = Integer.parseInt(input[1]);
                            long f = Fibonacci.fibonacciWithArray(n);
                            System.out.format("Fibonacci number with index %d is: %d%n", n, f);
                        }
                        else {
                            System.out.println("Command: FIB integer");
                        }
                        break;
                    case "BABY":
                        if(input.length == 2 && isNumeric(input[1])) {
                            double k = Double.parseDouble(input[1]);
                            double baby = BabylonianAlgorithm.babylonianAlgorithm(k);
                            System.out.format("Value of the squared root is %.2f%n", baby);
                        }
                        else {
                            System.out.println("Command: BABY double");
                        }
                        break;
                    case "MAX":
                        boolean all_num = true;
                        for(int i = 1; i < input.length; i++) {
                            all_num = isNumeric(input[i]);
                            if(!all_num){
                                break;
                            }
                        }
                        if(input.length >= 2 && all_num) {
                            int max = findMaximum(input);
                            System.out.format("The maximum number is: %d%n", max);
                        }
                        else {
                            System.out.println("Command: MAX list_of_integers");
                        }
                        break;
                    case "HELP":
                        printMenu();
                        System.out.println("Command: COMMAND ARGS");
                        break;
                    case "EXIT":
                        exit = true;
                        break;
                    default:
                        System.out.println("Error: please input your command again");
                }
            }
            else {
                System.out.println("Command: COMMAND ARGS");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Greetings Human !");
        System.out.println("Through this application you have access to the following commands :");
        System.out.println(" 1. Fibonacci number : FIB integer");
        System.out.println(" 2. Babylonian Algorithm : BABY double");
        System.out.println(" 3. Maximum number : MAX list_of_integers");
        System.out.println(" 4. Help menu : HELP");
        System.out.println(" 5. Exit application : EXIT");
    }

    public static int findMaximum(String[] s) {
        ArrayList<String> listOfStrings = new ArrayList<String>(Arrays.asList(s));
        listOfStrings.remove(0);
        int max = 0;
        for(int i = 0; i < listOfStrings.size(); i++) {
            int temp = Integer.parseInt(listOfStrings.get(i));
            if(temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}