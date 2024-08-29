package Dongji.components.persistences;

import Dongji.components.tasks.TaskList;

public interface Persistence {
    public void exportTasks();

    public TaskList importTasks();
}
