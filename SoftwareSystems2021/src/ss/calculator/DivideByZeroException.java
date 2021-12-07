package ss.calculator;

/**
 * Exception thrown by the {@link Calculator} when trying to divide by zero.
 */
public class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}
