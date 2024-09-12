package dongji.components.commands;

import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiIndexOutOfBoundException;

public class MarkCommand implements Command {
    private TaskList taskList;
    private int index;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Marks the task at the index provided from the constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        try {
            Task toMark = this.taskList.get(this.index);
            toMark.mark();
        } catch (DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }

        return "Nice! I've marked this task as done:";
    }

}
