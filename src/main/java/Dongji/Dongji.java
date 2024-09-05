package Dongji;

import Dongji.components.commands.Command;
import Dongji.components.parsers.CommandParser;
import Dongji.components.persistences.Persistence;
import Dongji.components.persistences.Txt;
import Dongji.components.tasks.TaskList;
import Dongji.exceptions.DongjiParseException;
import Dongji.exceptions.DongjiUnknownInstructionException;

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
        CommandParser parser = new CommandParser(this.taskList);

        Command currentCommand = null;
        try {
            currentCommand = parser.parseToCommand(input);
            return currentCommand.execute();
        }
        catch(DongjiUnknownInstructionException e) {
            return e.getMessage();
        }
        catch(DongjiParseException e) {
            return e.getMessage();
        }
    }
}
