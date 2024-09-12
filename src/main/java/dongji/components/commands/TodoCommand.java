package dongji.components.commands;

import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class TodoCommand implements Command {
    private TaskList taskList;
    private String taskName;

    public TodoCommand(TaskList taskList, String taskName) {
        this.taskList = taskList;
        this.taskName = taskName;
    }

    /**
     * Creates Todo task with task name provided from the constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        Task addedTodo;
        try {
            addedTodo = this.taskList.add(new Todo(taskName));
        } catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }
        return "added: " + addedTodo.getName();
    }
}
