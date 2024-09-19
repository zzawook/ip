package dongji.components.tasks;

import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String name) throws DongjiEmptyTaskNameException {
        super(name);
    }

    /**
     * Returns the string representation of the Todo object.
     * @return String
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
