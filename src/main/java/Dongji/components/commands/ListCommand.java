package Dongji.components.commands;

import Dongji.components.tasks.TaskList;

public class ListCommand implements Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    
    /** 
     * @return String
     */
    @Override
    public String execute() {
        return this.taskList.listTasks();
    }
}
