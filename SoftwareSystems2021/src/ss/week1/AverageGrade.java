package ss.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
    Suppose that a file named “testdata.txt” contains the following information: The first (solution)
    line of the file is the name of a student. Each of the next three lines contains an integer.
    The integers are the student’s scores on three exams. Write a program that will read
    the information in the file and display (on standard output) a message that contains the
    name of the student and the student’s average grade on the three exams. The average is
    obtained by adding up the individual exam grades and then dividing by the number of
    exams.
 */

public class AverageGrade {

    public static void main(String[] args) {
        double sum = 0.0;
        double averageGrade = 0.0;
        double counter = 0.0;
        String name = new String();
        try {
            File testFile = new File("C:\\Users\\janwillem.nijenhuis\\Documents\\SSHome\\SoftwareSystems2021\\src\\ss\\week1\\testdata.txt");
            Scanner myScanner = new Scanner(testFile);
            while(myScanner.hasNextLine()){
                String data = myScanner.nextLine();
                if(counter != 0.0) {
                    double temp = Double.parseDouble(data);
                    sum += temp;
                }
                else {
                    name = data;
                }
                counter += 1.0;
            }
        } catch(FileNotFoundException e) {
            System.out.println("An error occured while loading the file.");
            e.printStackTrace();
        }
        averageGrade = sum / (counter - 1.0);
        System.out.format("%s: %.2f", name, averageGrade);
    }
}