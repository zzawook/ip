package Dongji.components.commands;

import Dongji.components.DateTimeData;
import Dongji.components.tasks.Event;
import Dongji.components.tasks.Task;
import Dongji.components.tasks.TaskList;
import Dongji.exceptions.DongjiEmptyTaskNameException;

public class EventCommand implements Command {
    
    private TaskList taskList;
    private String taskName;
    private DateTimeData startDate;
    private DateTimeData endDate;

    public EventCommand(TaskList taskList, String taskName, DateTimeData startDate, DateTimeData endDate) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    /** 
     * Creates Event task with task name, start date and end date provided from constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        Task addedEvent;
        try {
            addedEvent = this.taskList.add(new Event(this.taskName, this.startDate, this.endDate));
        }
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }

        return "added: " + addedEvent.getName();
    }
}
