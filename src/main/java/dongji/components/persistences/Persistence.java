package dongji.components.persistences;

import dongji.components.tasks.TaskList;

public interface Persistence {
    public void exportTasks();

    public TaskList importTasks();
}
