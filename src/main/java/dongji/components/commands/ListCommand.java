package dongji.components.commands;

import dongji.components.tasks.TaskList;

/**
 * Command to list all tasks in the task list
 */
public class ListCommand implements Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Lists all tasks in the task list
     * 
     * @return String
     */
    @Override
    public String execute() {
        return this.taskList.listTasks();
    }
}
