package ss.week5.tictactoe;

import ss.week4.tictactoe.Board;
import ss.week4.tictactoe.Mark;
import ss.week4.tictactoe.Player;

public class ComputerPlayer extends Player {
    public Strategy strategy;
    public Mark mark;

    public ComputerPlayer(Mark mark, Strategy strategy) {
        super(strategy.getName() + "-computer-" + mark.toString(), mark);
        this.strategy = strategy;
        this.mark = mark;
    }

    public ComputerPlayer(Mark mark) {
        super("naive-computer-" + mark.toString(), mark);
        this.strategy = new NaiveStrategy();
    }

    @Override
    public int determineMove(Board board) {
        return strategy.determineMove(board, mark);
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}