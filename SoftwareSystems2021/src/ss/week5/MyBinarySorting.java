package ss.week5;

import java.io.*;
import java.util.Scanner;

/**
 * Makes an alphabetical list of all the words in a file selected
 * by the user.  The list can be written to a file.
 */
public class MyBinarySorting {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("C:\\Users\\janwillem.nijenhuis\\Documents\\SSHome\\SoftwareSystems2021\\src\\ss\\week5\\words.txt"));
        SortTreeDemo sortTreeDemo = new SortTreeDemo();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (sortTreeDemo.treeContains(sortTreeDemo.root, line)) {
                continue;
            } else {
                sortTreeDemo.treeInsert(line);
            }
        }
        FileWriter fw = new FileWriter("C:\\Users\\janwillem.nijenhuis\\Documents\\SSHome\\SoftwareSystems2021\\src\\ss\\week5\\output.txt");
        sortTreeDemo.treeList(sortTreeDemo.root, fw);
        fw.close();
    }
}