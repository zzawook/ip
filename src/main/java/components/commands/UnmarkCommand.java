package components.commands;

import components.tasks.Task;
import components.tasks.TaskList;
import exceptions.DongjiIndexOutOfBoundException;

public class UnmarkCommand implements Command {
    
    private TaskList taskList;
    private int index;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            Task toUnmark = this.taskList.get(index);
            toUnmark.unmark();
        }
        catch(DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }
        
        return "Okay, I've marked this task as not done yet";
    }
    
}
