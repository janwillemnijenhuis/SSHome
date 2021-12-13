package ss.week4;

public class TransposeFunction {

    public static void main(String[] args) {
        int[][] test = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                test[i][j] = (int) (Math.random() * 100);
            }
        }
        neatPrint(test);
        neatPrint(transposeArray(test));
    }

    public static int[][] transposeArray(int[][] array) {
        int rowLen = array.length;
        int colLen = array[0].length;
        int[][] result = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                int temp = array[i][j];
                result[i][j] = array[j][i];
                result[j][i] = temp;
            }
        }
        return result;
    }

    public static void neatPrint(int[][] array) {
        int rowLen = array.length;
        int colLen = array[0].length;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.printf("%-5s",array[i][j]);
            }
            System.out.printf("\n");
        }

        System.out.printf("\n");

    }


}
