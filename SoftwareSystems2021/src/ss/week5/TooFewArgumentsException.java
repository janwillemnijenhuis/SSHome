package ss.week5;

public class TooFewArgumentsException extends WrongArgumentException {

    public TooFewArgumentsException() {
        super("error: too few arguments provided");
    }
    public TooFewArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
