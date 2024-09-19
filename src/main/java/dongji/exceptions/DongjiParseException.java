package dongji.exceptions;

/**
 * Represents an exception when parsing the command from the
 * user fails.
 */
public class DongjiParseException extends DongjiException {
    public DongjiParseException(String message) {
        super(message);
    }
}
