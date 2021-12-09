package ss.week4.tictactoe;

import ss.utils.TextIO;

/**
 * Class for maintaining the Tic Tac Toe game.
 * Lab assignment Module 2
 * @author Theo Ruys en Arend Rensink
 */
public class Game {
    //@ private invariant board != null;

    public static final int NUMBER_PLAYERS = 2;

    /**
     * The board.
     */
    private Board board;

    /*@ public invariant players.length == NUMBER_PLAYERS;
        public invariant (\forall int i; (i >= 0 && i < NUMBER_PLAYERS); players[i] != null);
    @*/
    /**
     * The 2 players of the game.
     */
    public Player[] players;

    /**
     * Index of the current player.
     */

    private int current;

    // -- Constructors -----------------------------------------------
    /**
     * Creates a new Game object.
     * @param s0 the first player
     * @param s1 the second player
     */
    //@ requires s0 != null && s1 != null;
    public Game(Player s0, Player s1) {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        current = 0;
    }

    // -- Commands ---------------------------------------------------

    /**
     * Starts the Tic Tac Toe game. <br>
     * Asks after each ended game if the user want to continue. Continues until
     * the user does not want to play anymore.
     */
    public void start() {
        boolean continueGame = true;
        while (continueGame) {
            reset();
            play();
            System.out.println("\n> Play another time? (y/n)?");
            continueGame = TextIO.getBoolean();
        }
    }

    /**
     * Resets the game. <br>
     * The board is emptied and player[0] becomes the current player.
     */
    private void reset() {
        current = 0;
        board.reset();
    }

    /**
     * Plays the Tic Tac Toe game. <br>
     * First the (still empty) board is shown. Then the game is played
     * until it is over. Players can make a move one after the other. 
     * After each move, the changed game situation is printed.
     */
    private void play() {
        this.update();
        int playerMove = 0;
        while (!this.board.hasWinner() && !this.board.isFull()) {
            board.setField(this.players[playerMove].determineMove(this.board), this.players[playerMove].getMark());
            playerMove = (playerMove + 1) % 2;
            this.update();
        }
        printResult();
    }

    /**
     * Prints the game situation.
     */
    private void update() {
        System.out.println("\ncurrent game situation: \n\n" + board.toString()
                + "\n");
    }

    /**
     * Prints the result of the last game. <br>
     */
    //@ requires board.hasWinner() || board.isFull();
    private void printResult() {
        if (board.hasWinner()) {
            Player winner = board.isWinner(players[0].getMark()) ? players[0]
                    : players[1];
            System.out.println("Player " + winner.getName() + " ("
                    + winner.getMark().toString() + ") has won!");
        } else {
            System.out.println("Draw. There is no winner!");
        }
    }
}
