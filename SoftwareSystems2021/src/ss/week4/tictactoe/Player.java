package ss.week4.tictactoe;

/**
 * Abstract class for keeping a player in the Tic Tac Toe game. Module 2 lab
 * assignment.
 * 
 * 
 * @author Theo Ruys en Arend Rensink
 * @version $Revision: 1.4 $
 */
public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Mark mark;

    // -- Constructors -----------------------------------------------

    /**
     * Creates a new Player object.
     */
    /*@ requires name != null;
        requires mark == Mark.XX || mark == Mark.OO;
        ensures this.name == name;
        ensures this.mark == mark;
    @*/
    public Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mark of the player.
     */
    public Mark getMark() {
        return mark;
    }

    /**
     * Determines the field for the next move.
     * @param board the current game board
     * @return the player's choice
     */
    /*@ requires board != null && board.isFull() == false;
        ensures board.isField(\result) && board.getField(\result) == Mark.EMPTY;
    @*/
    public abstract int determineMove(Board board);

    // -- Commands ---------------------------------------------------

    /**
     * Makes a move on the board. <br>
     * @param board the current board
     */
    //@ requires board != null && board.isFull() == false;
    public void makeMove(Board board) {
        int choice = determineMove(board);
        board.setField(choice, getMark());
    }

}
