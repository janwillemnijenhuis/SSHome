package ss.week4;

import java.io.*;
import java.util.ArrayList;

public class ReadAndSort {

    public static void main(String[] args) throws IOException {

        // create readers and writers to facilitate I/O
        var sr = new BufferedReader(new InputStreamReader(System.in));
        var pr1 = new PipedReader(); var pw1 = new PipedWriter(pr1);
        var pr2 = new PipedReader(); var pw2 = new PipedWriter(pr2);
        var pw = new PrintWriter(pw1); var pw3 = new PrintWriter(System.out);
        var br = new BufferedReader(pr2);

        String line = null;
        int numbercount = 0;
        boolean exit = false;

        // loop over the input
        while (!exit) {
            try {
                line = sr.readLine();
                if (line.equals("0") || numbercount == 100) {
                    pw.close();
                    exit = true;
                } else {
                    pw.write(line + "\n");
                    numbercount++;
                }
            } catch (IOException e) {
                // do nothing yet
            }
        }
        IOSorter ioSorter = new IOSorter();
        ioSorter.process(pr1, pw2);
        String text = null;
        while ((text = br.readLine()) != null) {
            //System.out.println(text);
            pw3.write(text + "\n");
        }
        pw3.close();
    }
}

