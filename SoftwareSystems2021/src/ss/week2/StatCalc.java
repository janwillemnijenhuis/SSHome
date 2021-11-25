package ss.week2;
import java.lang.Math;
import ss.utils.TextIO;


public class StatCalc {

    private int count;   // Number of numbers that have been entered.
    private double sum;  // The sum of all the items that have been entered.
    private double squareSum;  // The sum of the squares of all the items.
    private double max;
    private double min;
    private static final String EXIT = "EXIT";
    private static final String STATS = "STATS";
    private static final String MENU = "MENU";

    public static void main(String[] args) {
        StatCalc calculator = new StatCalc();
        boolean exit = false;
        System.out.println(calculator.printMenu());
        while(!exit) {
            String input = TextIO.getlnString();
            switch(input) {
                case EXIT:
                    exit = true;
                    break;
                case STATS:
                    System.out.println(calculator.printStats());
                    exit = true;
                    break;
                case MENU:
                    System.out.println(calculator.printMenu());
                    break;
                default:
                    if(calculator.isNumeric(input)) {
                        calculator.enter(Double.parseDouble(input));
                    }
                    else {
                        System.out.println("Input not a number. Please try again.");
                    }
                    break;
            }
        }
    }

    public String printMenu() {
        return "Either input a number to add to the stats, type MENU for the menu, STATS for the final stats and EXIT to leave.";
    }

    public boolean isNumeric(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        try {
            Double.parseDouble(input);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Add a number to the dataset.  The statistics will be computed for all
     * the numbers that have been added to the dataset using this method.
     */
    public void enter(double num) {
        count++;
        sum += num;
        squareSum += num*num;
        max = Math.max(max, num);
        min = Math.min(min, num);
    }

    public String printStats() {
        return String.format("Count: %d, ", getCount())
                + String.format("Sum: %.2f, ", getSum())
                + String.format("Mean: %.2f, ", getMean())
                + String.format("SD: %.2f, ", getStandardDeviation())
                + String.format("Min: %.2f, ", getMin())
                + String.format("Max: %.2f.", getMax());
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    /**
     * Return the number of items that have been entered into the dataset.
     */
    public int getCount() {
        return count;
    }

    /**
     * Return the sum of all the numbers that have been entered.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Return the average of all the items that have been entered.
     * The return value is Double.NaN if no numbers have been entered.
     */
    public double getMean() {
        return sum / count;
    }

    /**
     * Return the standard deviation of all the items that have been entered.
     * The return value is Double.NaN if no numbers have been entered.
     */
    public double getStandardDeviation() {
        double mean = getMean();
        return Math.sqrt( squareSum/count - mean*mean );
    }

}  // end class StatCalc