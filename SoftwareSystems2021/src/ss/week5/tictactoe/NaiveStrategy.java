package ss.week5.tictactoe;

import ss.week4.tictactoe.Board;
import ss.week4.tictactoe.Mark;

public class NaiveStrategy implements Strategy {
    @Override
    public String getName() {
        return "Naive";
    }

    @Override
    public int determineMove(Board board, Mark mark) {
        // why would i bother with an array? this is way easier
        int move;
        do {
            move = (int) (Math.random() * 9);
        } while (!board.isEmptyField(move));
        return move;
    }
}