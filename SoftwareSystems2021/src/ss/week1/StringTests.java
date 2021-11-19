package ss.week1;

/*
    This exercise asks you to write a program that tests some of the built-in subroutines for
    working with Strings. The program should ask the user to enter their first name and their
    last name, separated by a space. Read the user’s response using TextIO.getln(). Break
    the input string up into two strings, one containing the first name and one containing the
    last name. You can do that by using the indexOf() subroutine to find the position of the
    space, and then using substring() to extract each of the two names. Also output the
    number of characters in each name, and output the user’s initials. (The initials are the
    first letter of the first name together with the first letter of the last name.)
 */
import ss.utils.TextIO;
public class StringTests {

    public static void main(String[] args) {
        System.out.println("Please enter your full name");
        String name = TextIO.getln();
        int pos = name.indexOf(" ");
        String firstName = name.substring(0, pos);
        String lastName = name.substring(pos + 1);
        int firstLen = pos - 1;
        int lastLen = lastName.length();
        System.out.println("Your first name is " + firstName + ", which has " + firstLen + " characters.");
        System.out.println("Your first name is " + lastName + ", which has " + lastLen + " characters.");
        System.out.println("Your initials are: " + firstName.substring(0,1) + lastName.substring(0,1));
    }
}