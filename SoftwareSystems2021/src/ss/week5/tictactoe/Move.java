package ss.week5.tictactoe;

public enum Move {
    WINNING(1),
    LOSING(-1),
    NEUTRAL(0),
    NONE(-2);

    public final int label;

    Move(int i) {
        this.label = i;
    }

    public boolean compare(Move bestQual) {
        if (this.label > bestQual.label) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Move bestQual) {
        if (this.label == bestQual.label) {
            return true;
        } else {
            return false;
        }
    }
}
