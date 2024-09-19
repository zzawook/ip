package dongji.components.commands;

/**
 * Interface for all commands that defines behavior of all command. 
 * Obeys Command design pattern.
 */
public interface Command {

    /**
     * Executes the given command and returns the response as a String
     * 
     * @return String
     */
    public String execute();
}
