package dongji.components.commands;

import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Command to add a Todo task to the task list
 */
public class TodoCommand implements Command {
    private TaskList taskList;
    private String taskName;

    /**
     * Constructor for TodoCommand
     * 
     * @param taskList
     * @param taskName
     */
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
