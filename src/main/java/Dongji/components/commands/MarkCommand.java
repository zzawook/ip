package Dongji.components.commands;

import Dongji.components.tasks.Task;
import Dongji.components.tasks.TaskList;
import Dongji.exceptions.DongjiIndexOutOfBoundException;

public class MarkCommand implements Command {
    private TaskList taskList;
    private int index;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    
    /** 
     * @return String
     */
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
