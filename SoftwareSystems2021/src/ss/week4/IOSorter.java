package ss.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOSorter {

    List<Double> list = new ArrayList<>();

    public IOSorter() {}

    public void process(Reader input, Writer output) throws IOException {
        var br = new BufferedReader(input);
        var pw = new PrintWriter(output);

        String line = null;

        while ((line = br.readLine()) != null) {
            try {
                list.add(Double.parseDouble(line.strip()));
            } catch (NumberFormatException e) {
                // skip this line
            }
        }
        List<Double> sortedList = new ArrayList<>(MergeSort.mergeSort(list));

        for (int i = 0; i < sortedList.size(); i++) {
            pw.write(sortedList.get(i)+ "\n");
        }
        pw.close();
    }
}
