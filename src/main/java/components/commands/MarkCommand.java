package components.commands;

import components.tasks.Task;
import components.tasks.TaskList;
import exceptions.DongjiIndexOutOfBoundException;

public class MarkCommand implements Command {
    private TaskList taskList;
    private int index;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            Task toMark = this.taskList.get(this.index);
            toMark.mark();
        }
        catch (DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }
        
        return "Nice! I've marked this task as done:";
    }
    
}
