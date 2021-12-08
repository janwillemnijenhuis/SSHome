package ss.week4.tictactoe;

import org.w3c.dom.Text;
import ss.utils.TextIO;

public class TicTacToe {
    public static void main(String[] args) {
        String[] names = checkArgs(args);
        Player p1 = new Player(names[0], Mark.XX) {
            @Override
            public int determineMove(Board board) {
                int move;
                do {
                    move = (int) (Math.random() * 9);
                } while (!board.isEmptyField(move));
                return move;
            }
        };
        Player p2 = new Player(names[1], Mark.OO) {
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

    public static String[] checkArgs(String[] args) {
        String[] names = new String[2];
        if (args.length >= 2) {
            names[0] = args[0];
            names[1] = args[1];
        } else if (args.length == 1) {
            names[0] = args[0];
            System.out.println("Please input the name of the second player: ");
            names[1] = TextIO.getlnString();
        } else {
            System.out.println("Please input the name of the first player: ");
            names[0] = TextIO.getlnString();
            System.out.println("Please input the name of the second player: ");
            names[1] = TextIO.getlnString();
        }
        return names;
    }
}