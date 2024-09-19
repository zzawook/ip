package dongji.components.commands;

import dongji.components.DateTimeData;
import dongji.components.tasks.Deadline;
import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Command to add a Deadline task to the task list
 */
public class DeadlineCommand implements Command {

    private TaskList taskList;
    private String taskName;
    private DateTimeData deadline;

    public DeadlineCommand(TaskList taskList, String taskName, DateTimeData deadlineDate) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.deadline = deadlineDate;
    }

    /**
     * Creates a new Deadline task with task name and deadline date provided from
     * constructor
     *
     * @return String
     */
    @Override
    public String execute() {
        Task addedDeadline;
        try {
            addedDeadline = this.taskList.add(new Deadline(taskName, deadline));
        } catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }

        return "added: " + addedDeadline.getName();
    }
}
