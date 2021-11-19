package ss.week1;
import ss.utils.TextIO;

public class GrossAndDozens {
    /**
     * Computes the amount of gross, dozens, and individual eggs after user input.
     */
    public static void main(String[] args) {

        System.out.println("Please provide us with the number of eggs you currently own:");

        int n = TextIO.getlnInt();

        int g = n / 144;
        int d = (n - g * 144) / 12;
        int r = n - g * 144 - d * 12;

        System.out.format("The number of eggs is %d gross, %d dozen and %d%n", g, d, r);

    }
}