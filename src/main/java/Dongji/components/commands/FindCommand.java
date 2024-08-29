package Dongji.components.commands;

import Dongji.components.tasks.TaskList;
import Dongji.exceptions.DongjiIndexOutOfBoundException;

public class FindCommand implements Command {

    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    /**
     * @return String
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        int taskIndex = 1;

        try {
            for (int i = 0; i < this.taskList.size(); i++) {
                if (this.taskList.get(i).getName().contains(this.keyword)) {
                    sb.append(taskIndex + ". " + this.taskList.get(i).toString() + "\n");
                    taskIndex++;
                }
            }
        } catch (DongjiIndexOutOfBoundException e) {
            return e.getMessage();
        }

        return sb.toString();
    }
}
