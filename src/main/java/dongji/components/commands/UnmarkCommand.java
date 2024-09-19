package dongji.components.commands;

import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiIndexOutOfBoundException;

/**
 * Command to mark a task as not done (Unmark)
 */
public class UnmarkCommand implements Command {

    private TaskList taskList;
    private int index;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Unmarks the task at the index provided from the constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        try {
            Task toUnmark = this.taskList.get(index);
            toUnmark.unmark();
        } catch (DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }

        return "Okay, I've marked this task as not done yet";
    }

}
