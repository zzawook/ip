package Dongji.components.commands;

import Dongji.components.tasks.TaskList;

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
