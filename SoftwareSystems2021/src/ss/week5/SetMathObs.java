package ss.week5;

import java.io.*;
import java.util.*;


/**
 * This Class receives an I/O stream and processes the commands to perform mathematical set operations
 * @author Jan Willem Nijenhuis
 */
public class SetMathObs {

    /**
     * constructor
     */
    public SetMathObs() {

    }

    /**
     * gets input from I/O, computes the desired result and prints this to the I/O
     */
    public void process(Reader input, Writer output) {
        BufferedReader br = new BufferedReader(input);
        PrintWriter pw = new PrintWriter(output, true);

        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] processedLine = processArgs(line);
                if (processedLine.length == 3) {
                    TreeSet<Integer> A = processSet(processedLine[0]);
                    TreeSet<Integer> B = processSet(processedLine[2]);
                    switch (processedLine[1]) {
                        case "+":
                            pw.println(union(A, B));
                            break;
                        case "*":
                            pw.println(intersection(A, B));
                            break;
                        case "-":
                            pw.println(difference(A, B));
                            break;
                        default:
                            pw.println("error: please input like [A] operator [B]");
                            break;
                    }
                } else {
                    pw.println("error: please input like [A] operator [B]");
                }
            }
        } catch (IOException e) {
            pw.println(e.getMessage());
        }
    }

    /**
     * processes a string of data and splits it into the components desired
     * @param line the line read from I/O
     * @return the array of string inputs
     */
    public String[] processArgs(String line) {
        return line.split(" ");
    }

    /**
     * processes the string input for a set to the actual Treeset
     * @param arg the string set
     * @return the Tree set
     */
    public TreeSet<Integer> processSet(String arg) {
        String[] numbers = arg.replace('[', ' ').replace(']', ' ').strip().split(",");
        List<Integer> list = new ArrayList<Integer>();
        for (String num: numbers) {
            list.add(Integer.parseInt(num));
        }
        TreeSet<Integer> result = new TreeSet<>(list);
        return result;
    }

    /**
     * computes the union of two sets
     * @param A first set
     * @param B second set
     * @return set containing the values in A and B
     */
    public Set<Integer> union(Set<Integer> A, Set<Integer> B) {
        TreeSet<Integer> union = new TreeSet<>();
        union.addAll(A);
        union.addAll(B);
        return union;
    }

    /**
     * computes the intersection of two sets
     * @param A first set
     * @param B second set
     * @return set containing the values both in A and B
     */
    public Set<Integer> intersection(Set<Integer> A, Set<Integer> B) {
        TreeSet<Integer> intersection = new TreeSet<>();
        intersection.addAll(A);
        intersection.retainAll(B);
        return intersection;
    }

    /**
     * computes the difference of two sets
     * @param A first set
     * @param B second set
     * @return set containing the values in A but not in B
     */
    public Set<Integer> difference(Set<Integer> A, Set<Integer> B) {
        TreeSet<Integer> difference = new TreeSet<>();
        difference.addAll(A);
        difference.removeAll(B);
        return difference;
    }

}
