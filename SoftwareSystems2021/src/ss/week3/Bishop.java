package ss.week3;
import java.lang.Math;

public class Bishop implements Piece {
    private int currentRow;
    private int currentColumn;

    public Bishop(int row, int column) {
        this.currentRow = row;
        this.currentColumn = column;
    }

    @Override
    public boolean validMove(int row, int column) {
        if((Math.abs(this.currentRow - row) != Math.abs(this.currentColumn - column)) || (row > 8 || row < 1 || column > 8 || column < 1)) {
            return false;
        }
        return true;
    }

    @Override
    public void moveTo(int row, int column) {
        if(validMove(row, column)) {
            this.currentColumn = column;
            this.currentRow = row;
        }
    }

    @Override
    public int getColumn() {
        return this.currentColumn;
    }

    @Override
    public int getRow() {
        return this.currentRow;
    }
}
