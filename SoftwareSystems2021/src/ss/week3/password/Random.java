package ss.week3.password;

import java.lang.Math;

public class Random {
    private int length;

    public Random(int length) {
        this.length = length;
    }

    public String randomString() {
        String total = "";
        for(int i = 0; i < this.length; i++) {
            if(Math.random() <= 0.5) {
                total += genDigit();
            } else {
                total += genLetter();
            }
        }
        return total;
    }

    public char genLetter() {
        return (char) ('a' + 26*Math.random());
    }

    public char genDigit() {
        return (char) ('0' + 10*Math.random());
    }
}