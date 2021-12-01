package ss.week2;

public class Simulation {

    public static final int SIMS = 11;
    public static Double[][] results = new Double[SIMS][3];
    public static void main(String[] args) {
        for(int i = 0; i < SIMS; i++) {
            StatCalc stats = new StatCalc();
            for(int j = 0; j < 10000; j++) {
                PairOfDice dice = new PairOfDice();
                do {
                    dice.roll();
                } while (dice.getSum() != i + 2);
                stats.enter(dice.getRolls());
            }
            results[i][0] = stats.getMean();
            results[i][1] = stats.getStandardDeviation();
            results[i][2] = stats.getMax();
        }
        printMatrix(results);
    }

    public static void printMatrix(Double[][] matrix) {
        for(int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] < 10) {
                    System.out.printf(" ");
                }
                System.out.printf(" %.2f ", matrix[row][col]);
            }
            System.out.println();
        }
    }

}