package dongji;

import dongji.components.commands.Command;
import dongji.components.parsers.CommandParser;
import dongji.components.persistences.Persistence;
import dongji.components.persistences.Txt;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiParseException;
import dongji.exceptions.DongjiUnknownInstructionException;

/**
 * Dongji backend application that processed commands and returns responses.
 */
public class Dongji {

    private TaskList taskList;
    private Persistence persistence;

    public Dongji() {
        this.taskList = new TaskList();
        this.persistence = new Txt(taskList);
        this.persistence.importTasks();
    }

    public void terminate() {
        this.persistence.exportTasks();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        CommandParser parser = new CommandParser(this.taskList, this);

        Command currentCommand = null;
        try {
            currentCommand = parser.parseToCommand(input);
            return currentCommand.execute();
        } catch (DongjiUnknownInstructionException e) {
            return e.getMessage();
        } catch (DongjiParseException e) {
            return e.getMessage();
        }
    }
}
