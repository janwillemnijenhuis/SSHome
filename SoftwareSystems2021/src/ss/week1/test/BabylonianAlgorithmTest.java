package ss.week1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ss.week1.BabylonianAlgorithm;

public class BabylonianAlgorithmTest {

    public double round(double value, int places) {
        double factor = Math.pow(10, places);
        value = value * factor;
        return Math.floor(value) / factor;
    }

    @Test
    void babylonianAlgorithmTest() {
        for (int i = 1; i <= 100; i++) {
            double guessedNumber = BabylonianAlgorithm.babylonianAlgorithm(i);
            assertEquals(round(guessedNumber, 2), round(Math.sqrt(i), 2));
        }
    }
}
