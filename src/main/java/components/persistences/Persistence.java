package components.persistences;

import components.tasks.TaskList;

public interface Persistence {
    public void exportTasks();

    public TaskList importTasks();
}
