package dongji.components.tasks;

import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Represents a Task. Abstract class that is extended by other specific task types.
 */
public abstract class Task {
    private String name;
    private boolean isMarked;

    public Task(String name) throws DongjiEmptyTaskNameException {
        if (name.trim().length() == 0) {
            throw new DongjiEmptyTaskNameException("Task name cannot be empty! Please provide a task name");
        }
        this.name = name;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return boolean
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    private String generateMarkString() {
        return this.isMarked ? "[X] " : "[ ] ";
    }

    public String toString() {
        return this.generateMarkString() + this.name;
    }
}
