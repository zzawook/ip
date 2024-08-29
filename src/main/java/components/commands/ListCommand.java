package components.commands;

import components.tasks.TaskList;

public class ListCommand implements Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return this.taskList.listTasks();
    }
}
