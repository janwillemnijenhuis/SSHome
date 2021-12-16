package ss.week5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exercise 7.6 asked you to read a file, make an alphabetical list of all the words that occur (solution)
 * in the file, and write the list to another file. In that exercise, you were asked to use an
 * ArrayList<String> to store the words. Write a new version of the same program that stores
 * the words in a binary sort tree instead of in an arraylist. You can use the binary sort tree
 * routines from SortTreeDemo.java, which was discussed in Subsection 9.4.2.
 *
 * @author Jan Willem Nijenhuis, Elisa Verhoeven
 * @version 0.0.1
 */

public class MyBinarySortTree {

    public static void main(String[] args) {
        MyBinarySortTree tree = new MyBinarySortTree();
        tree.readFile("C:\\Users\\janwillem.nijenhuis\\Documents\\SSHome\\SoftwareSystems2021\\src\\ss\\week5\\words.txt");
    }

    public MyBinarySortTree() {}

    public List<String> readFile(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> wordsList = new ArrayList<>();
        while (sc.hasNext()) {
            wordsList.add(sc.next());
        }
        return wordsList;
    }
}
