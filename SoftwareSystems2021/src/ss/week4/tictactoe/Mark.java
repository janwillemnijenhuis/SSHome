package ss.week4.tictactoe;

/**
 * Represents a mark in the Tic Tac Toe game. There three possible values:
 * Mark.XX, Mark.OO and Mark.EMPTY.
 * Module 2 lab assignment
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public enum Mark {
    
    EMPTY, XX, OO;

    /**
     * Returns the other mark.
     * @return the other mark is this mark is not EMPTY or EMPTY
     */
    //@ ensures this == XX ==> \result == OO && this == OO ==> \result == XX;
    public Mark other() {
        if (this == XX) {
            return OO;
        } else if (this == OO) {
            return XX;
        } else {
            return EMPTY;
        }
    }
}
