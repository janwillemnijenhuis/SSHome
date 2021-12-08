package ss.week4.tictactoe;

public class TicTacToe {
    public static void main(String[] args) {
        String name1 = args[0];
        String name2 = args[1];
        Player p1 = new Player(name1, Mark.XX) {
            @Override
            public int determineMove(Board board) {
                int move;
                do {
                    move = (int) (Math.random() * 9);
                } while (!board.isEmptyField(move));
                return move;
            }
        };
        Player p2 = new Player(name2, Mark.OO) {
            @Override
            public int determineMove(Board board) {
                int move;
                do {
                    move = (int) (Math.random() * 9);
                } while (!board.isEmptyField(move));
                return move;
            }
        };

        Game game = new Game(p1, p2);
        game.start();
    }
}