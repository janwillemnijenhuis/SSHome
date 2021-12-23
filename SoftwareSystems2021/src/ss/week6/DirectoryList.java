package ss.week6;

import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {


    public static void main(String[] args) {

        String directoryName;  // Directory name entered by the user.
        File directory;        // File object referring to the directory.
        String[] files;        // Array of file names in the directory.
        Scanner scanner;       // For reading a line of input from the user.

        scanner = new Scanner(System.in);  // scanner reads from standard input.

        System.out.print("Enter a directory name: ");
        directoryName = scanner.nextLine().trim();
        directory = new File(directoryName);

        if (directory.isDirectory() == false) {
            if (directory.exists() == false)
                System.out.println("There is no such directory!");
            else
                System.out.println("That file is not a directory.");
        }
        else {
            recursiveListing(directory, "");
        }
    } // end main()

    public static void recursiveListing(File directory, String delimiter) {
        String[] files = directory.list();
        String[] directoryName = directory.toString().split("\\\\");
        String directoryNametemp = directoryName[directoryName.length - 1];
        System.out.println(delimiter + directoryNametemp);
        delimiter = delimiter + "     ";
        for (int i = 0; i < files.length; i++) {
            String filename = directory.toString() + "/" + files[i];
            File file = new File(filename);
            if (file.isDirectory() == false) {
                System.out.println(delimiter + "|----" + files[i]);
            } else {
//                File newDirectory = new File(directory.toString() + "/" + file.toString());
                recursiveListing(file, delimiter);
            }
        }
    }
} // end class DirectoryList
