package ss.week2;
import ss.utils.TextIO;
/**
 * Student class, which will contain a method <b>passed</b> 
 * which will return whether a student has passed or not. 
 * @author Wim Kamerman
 */
public class Student {
	
	private static final int CRITERION = 70;
	private static int score;

	public static void main(String[] args) {
		System.out.print("Please enter your test score: ");
		score = TextIO.getlnInt();
		System.out.format("%b%n", passed());
	}

	public static boolean passed() {
		boolean pass = score >= CRITERION;
	    return pass;
	}
	
}