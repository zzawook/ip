package components.commands;

import components.DateTimeData;
import components.tasks.Deadline;
import components.tasks.Task;
import components.tasks.TaskList;
import exceptions.DongjiEmptyTaskNameException;

public class DeadlineCommand implements Command {
    
    private TaskList taskList;
    private String taskName;
    private DateTimeData deadline;

    public DeadlineCommand(TaskList taskList, String taskName, DateTimeData deadlineDate) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.deadline = deadlineDate;
    }

    @Override
    public String execute() {
        Task addedDeadline;
        try {
            addedDeadline = this.taskList.add(new Deadline(taskName, deadline));
        } 
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }

        return "added: " + addedDeadline.getName();
    }
}
