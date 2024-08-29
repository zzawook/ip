package components.commands;

import components.tasks.Deadline;
import components.tasks.Task;
import components.tasks.TaskList;
import exceptions.DongjiEmptyTaskNameException;

public class DeadlineCommand implements Command {
    
    private TaskList taskList;
    private String deadlineDate;
    private String taskName;

    public DeadlineCommand(TaskList taskList, String taskName, String deadlineDate) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String execute() {
        Task addedDeadline;
        try {
            addedDeadline = this.taskList.add(new Deadline(taskName, deadlineDate));
        } 
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }

        return "added: " + addedDeadline.getName();
    }
}
