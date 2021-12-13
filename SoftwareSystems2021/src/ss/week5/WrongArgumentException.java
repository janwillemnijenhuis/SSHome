package ss.week5;

public class WrongArgumentException extends Exception {

    public WrongArgumentException() {
        super();
    }

    public WrongArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
