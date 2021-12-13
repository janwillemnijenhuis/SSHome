package ss.week5.tictactoe;

import ss.utils.TextIO;
import ss.week4.tictactoe.*;

public class TicTacToe {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        // moeten de players allemaal in dezelfde map staan? nu moet ik ze deze assignment geven
        // of ligt het eraan dat ik eerst alles van week 4 geimport had

        players[1] = new HumanPlayer("test", Mark.XX);
        players[0] = new ComputerPlayer(Mark.OO);
        Mark[] marks = {Mark.XX, Mark.OO};
        String[] names = checkArgs(args);

        int i = 0;
        do {
            if (names[i].equals("-N")) {
                players[i] = new ComputerPlayer(marks[i]);
            } else if (names[i].equals("-S")) {
                players[i] = new ComputerPlayer(marks[i], new SmartStrategy());
            } else {
                players[i] = new HumanPlayer(names[i], marks[i]);
            }
            i++;
        } while (i < names.length);

        Game game = new Game(players[0], players[1]);
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