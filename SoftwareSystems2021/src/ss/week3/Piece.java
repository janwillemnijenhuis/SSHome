package ss.week3;

public interface Piece {
    public boolean validMove(int row, int column);
    public void moveTo(int row, int column);
    public int getColumn();
    public int getRow();
}

