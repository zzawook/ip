package dongji.exceptions;

/**
 * Represents an exception in Dongji.
 */
public class DongjiException extends Exception {
    public DongjiException(String message) {
        super("OOPS!!! " + message);
    }
}
