package ss.week5.tictactoe;

import ss.week4.tictactoe.Board;
import ss.week4.tictactoe.Mark;

public interface Strategy {
    /**
     * @return the name of this strategy
     */
    public String getName();

    /**
     * determines the move of the player
     * @param board current state of the board
     * @param mark the mark of this player
     * @return the move to make
     */
    public int determineMove(Board board, Mark mark);
}