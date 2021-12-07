package ss.week3;

public class Rook implements Piece {
    private int currentRow;
    private int currentColumn;

    public Rook(int row, int column) {
        this.currentRow = row;
        this.currentColumn = column;
    }

    @Override
    public boolean validMove(int row, int column) {
        if((row - this.currentRow != 0 && column - this.currentColumn != 0) ||
            (row > 8 || row < 1 || column > 8 || column < 1)) {
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
