package dongji.exceptions;

/**
 * Represents an exception when attempting to create task with 
 * empty task name.
 */
public class DongjiEmptyTaskNameException extends DongjiException {
    public DongjiEmptyTaskNameException(String message) {
        super(message);
    }
}
