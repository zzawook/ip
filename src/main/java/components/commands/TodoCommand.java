package components.commands;

import components.tasks.Task;
import components.tasks.TaskList;
import components.tasks.Todo;
import exceptions.DongjiEmptyTaskNameException;

public class TodoCommand implements Command {
    private TaskList taskList;
    private String taskName;

    public TodoCommand(TaskList taskList, String taskName) {
        this.taskList = taskList;
        this.taskName = taskName;
    }

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
