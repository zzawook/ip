package dongji.exceptions;

/**
 * Represents an exception when an unknown command from the user is encountered.
 */
public class DongjiUnknownInstructionException extends DongjiException {
    public DongjiUnknownInstructionException(String message) {
        super(message);
    }
}
