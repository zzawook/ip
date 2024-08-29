package Dongji.components.commands;

import Dongji.components.tasks.Task;
import Dongji.components.tasks.TaskList;
import Dongji.components.tasks.Todo;
import Dongji.exceptions.DongjiEmptyTaskNameException;

public class TodoCommand implements Command {
    private TaskList taskList;
    private String taskName;

    public TodoCommand(TaskList taskList, String taskName) {
        this.taskList = taskList;
        this.taskName = taskName;
    }

    
    /** 
     * @return String
     */
    @Override
    public String execute() {
        Task addedTodo;
        try {
            addedTodo = this.taskList.add(new Todo(taskName));
        }
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }
        return "added: " + addedTodo.getName();
    }    
}
