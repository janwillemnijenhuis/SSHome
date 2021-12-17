package ss.week5.tictactoe;

import ss.week4.tictactoe.Board;
import ss.week4.tictactoe.Mark;

public class SmartStrategy implements Strategy {
    NaiveStrategy naiveStrategy = new NaiveStrategy();
    @Override
    public String getName() {
        return "Smart";
    }

    @Override
    public int determineMove(Board board, Mark mark) {

        int winningMove = winningMove(board.deepCopy(), mark);
        int blockWinningMove = blockWinningMove(board.deepCopy(), mark);

        if (winningMove != -1) {
            return winningMove;
        } else if (blockWinningMove != -1) {
            return blockWinningMove;
        } else if (board.isEmptyField(4)) {
            return 4;
        }
        return naiveStrategy.determineMove(board, mark);
    }

    /**
     * Checks if there is a winning move for this player
     * @param board the current state of the board
     * @param mark the mark of this player
     * @return 0-8 for the winning move, -1 if there is none
     */
    public int winningMove(Board board, Mark mark) {
        int i = 0;
        do {
            if (board.isEmptyField(i)) {
                board.setField(i, mark);
                if (board.isWinner(mark)) {
                    return i;
                } else {
                    board.setField(i, Mark.EMPTY);
                }
            }
            i++;
        } while (i < Board.DIM * Board.DIM);
        return -1;
    }

    /**
     * Checks if there is a move which should be blocked to prevent the opponent from winning
     * @param board the current state of the board
     * @param mark the mark of this player
     * @return 0-8 for the blocking move, -1 if there is none
     */
    public int blockWinningMove(Board board, Mark mark) {
        int i = 0;
        do {
            if (board.isEmptyField(i)) {
                board.setField(i, mark.other());
                if (board.isWinner(mark.other())) {
                    return i;
                } else {
                    board.setField(i, Mark.EMPTY);
                }
            }
            i++;
        } while (i < Board.DIM * Board.DIM);
        return -1;
    }
}
