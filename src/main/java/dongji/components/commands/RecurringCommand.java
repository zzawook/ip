package dongji.components.commands;

import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiEmptyTaskNameException;
import dongji.components.tasks.Task;
import dongji.components.tasks.Recurring;

public class RecurringCommand implements Command {

    private TaskList taskList;
    private String taskName;
    private String cron;

    public RecurringCommand(TaskList taskList, String taskName, String cron) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.cron = cron;
    }

    @Override
    public String execute() {
        Task recurringTask;
        try {
            recurringTask = this.taskList.add(new Recurring(this.taskName, this.cron));
        }
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }
        return "added " + recurringTask.getName();
    }
    
}
