package ss.week5;

public class ArgumentLengthsDifferException extends WrongArgumentException {

    public ArgumentLengthsDifferException(String errorMessage) {
        super(errorMessage);
    }
    public ArgumentLengthsDifferException(int required, int found) {
        super("Argument lenghts differ, expected " + required + " found " + found);
    }
}
