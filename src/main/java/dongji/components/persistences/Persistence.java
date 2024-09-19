package dongji.components.persistences;

import dongji.components.tasks.TaskList;

/**
 * Interface for exporting and importing tasks
 */
public interface Persistence {
    /**
     * Exports tasks to a file
     */
    public void exportTasks();

    /**
     * Imports tasks from a file
     * 
     * @return TaskList
     */
    public TaskList importTasks();
}
