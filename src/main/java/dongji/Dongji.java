package dongji;

import dongji.components.commands.Command;
import dongji.components.parsers.CommandParser;
import dongji.components.persistences.Persistence;
import dongji.components.persistences.Txt;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiException;

/**
 * Dongji backend application that processes commands and returns responses.
 */
public class Dongji {

    private TaskList taskList;
    private Persistence persistence;

    /**
     * Constructor for Dongji
     */
    public Dongji() {
        this.taskList = new TaskList();
        this.persistence = new Txt(taskList);
        this.persistence.importTasks();
    }

    /**
     * Terminates the application and exports the tasks to the persistence layer.
     */
    public void terminate() {
        this.persistence.exportTasks();
    }

    /**
     * Returns a welcome message for the user.
     * @return String
     */
    public String welcomeMessage() {
        return "Hello! I'm Dongji, your Personal To-do Companion!\nWhat can I do for you?";
    }

    /**
     * Generates a response for the user's chat message.
     * @param input
     * @return String
     */
    public String getResponse(String input) {
        CommandParser parser = new CommandParser(this);

        Command currentCommand = null;
        try {
            currentCommand = parser.parseToCommand(input);
            return currentCommand.execute();
        } catch (DongjiException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the TaskList object.
     * 
     * @return
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
