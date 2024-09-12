package Dongji.components.commands;

import java.util.List;

import Dongji.components.tasks.Task;
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
     * Finds tasks that contain the keyword provided from the constructor
     * 
     * @return String
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        int taskIndex = 1;

        List<Task> filteredTaskList = this.taskList.stream().filter(task -> task.getName().contains(this.keyword))
                .toList();

        for (int i = 0; i < filteredTaskList.size(); i++) {
            sb.append(taskIndex + ". " + filteredTaskList.get(i).toString() + "\n");
            taskIndex++;
        }

        return sb.toString();
    }
}
