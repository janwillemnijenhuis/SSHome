package ss.week5;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * This program reads from I/O and processes the input to perform mathematical set operators
 * @author Jan Willem Nijenhuis
 */

public class IOMathSetObs {

    /**
     * generates SetMathObs object and processes input/output from the terminal
     * @param args
     */
    public static void main(String[] args) {

        SetMathObs setMathObs = new SetMathObs();

        InputStreamReader si = new InputStreamReader(System.in);
        OutputStreamWriter so = new OutputStreamWriter(System.out);

        setMathObs.process(si, so);

    }

}
