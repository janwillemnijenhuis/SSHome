package ss.week5.tictactoe;

import ss.week4.tictactoe.Board;
import ss.week4.tictactoe.Mark;

public class PerfectStrategy implements Strategy {
    NaiveStrategy naiveStrategy = new NaiveStrategy();
    /**
     * get the name of the strategy
     * @return name
     */
    @Override
    public String getName() {
        return "Perfect";
    }

    /**
     * Determine the move of the perfect player
     * @param board current state of the board
     * @param mark the mark of this player
     * @return the field to place your mark
     */
    @Override
    public int determineMove(Board board, Mark mark) {
        int i = 0;
        Tuple2<Integer, Move> best = new Tuple2<>(-1, Move.NONE); // set the initial best move at -1 and None
        do {
            if (board.isEmptyField(i)) { // if the field is full, continue loop
                Tuple2<Integer, Move> state = recursiveAlgorithm(tryMove(board.deepCopy(), i, mark), i, mark); // go into recursive to find best move
                if (state.getSecond().compare(best.getSecond())) {
                    best = state; // if this move is better than current best state then change to this move
                } else if (state.getSecond().equals(best.getSecond())) {
                    double rand = Math.random(); // add some randomness to changing the move if the move is equal
                    if (rand > 0.5) {
                        best = state; // if move is equal to best move, change it with Pr(0.5)
                    }
                }
            }
            i++;
        } while (i < board.DIM * board.DIM);
        if (best.equals(Move.NONE) || board.equals(Move.LOSING)) {
            return naiveStrategy.determineMove(board, mark); // if there is no better move return the move according to naive strategy
        }
        return best.getFirst(); // return the best move if its either neutral or winning
    }

    /**
     * does the move on a copy of the board
     * @param boardCopy copy of the board
     * @param move the move to make
     * @param mark the mark of the player
     * @return the copy of the board including the move
     */
    public Board tryMove(Board boardCopy, int move, Mark mark) {
        boardCopy.setField(move, mark);
        return boardCopy;
    }

    /**
     * loops over all possible moves by opponents recursively
     * @param board the current state of the board
     * @param move the move made by player with mark
     * @param mark the mark of the player
     * @return a tuple containing the value of the field and the enum state of the player
     */
    public Tuple2<Integer, Move> recursiveAlgorithm(Board board, int move, Mark mark) {

        board.setField(move, mark); // set the field to the mark of this player

        if (board.isWinner(mark)) {
            return new Tuple2<>(move, Move.WINNING); // if this player is now the winner after this move, terminate the loop
        }

        // if not winning then determine the best move of the opponent
        int i = 0;
        do {
            if (board.isEmptyField(i)) { // check if the field is empty, otherwise continue
                Tuple2<Integer, Move> oppMove = recursiveAlgorithm(board, i, mark.other()); // obtain the best move of the opponent
                Board oppBoard = tryMove(board, oppMove.getFirst(), mark.other()); // retrieve the state of the board after the best move of the opponent
                if (oppBoard.isWinner(mark.other())) {
                    return new Tuple2<Integer, Move>(move, Move.LOSING); // if opponent is winner return player as losing
                } else if (oppMove.getSecond().compare(Move.LOSING)) {
                    return new Tuple2<Integer, Move>(move, Move.WINNING); // if opponent is loser return move as winning
                }
            }
            i++;
        } while (i < board.DIM * board.DIM);
        return new Tuple2<>(move, Move.NEUTRAL); // if no moves are possible, or none are winning/losing return neutral
    }
}

