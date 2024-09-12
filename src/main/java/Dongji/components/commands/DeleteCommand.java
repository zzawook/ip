package dongji.components.commands;

import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiIndexOutOfBoundException;

public class DeleteCommand implements Command {
    private TaskList taskList;
    private int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Deletes the task at the index provided from the constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        Task deletedTask;
        try {
            deletedTask = this.taskList.deleteTask(index);
        } catch (DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }

        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + this.taskList.size()
                + " tasks in the list.";
    }
}
