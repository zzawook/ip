package dongji.exceptions;

public class DongjiException extends Exception {
    public DongjiException(String message) {
        super("OOPS!!! " + message);
    }
}
