package dongji.components.tasks;

import dongji.components.DateTimeData;
import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private DateTimeData deadline;

    /**
     * Constructor for Deadline
     * 
     * @param name
     * @param deadline
     * @throws DongjiEmptyTaskNameException
     */
    public Deadline(String name, DateTimeData deadline) throws DongjiEmptyTaskNameException {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     * 
     * @return DateTimeData
     */
    public DateTimeData getDeadline() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeData.formatDate(this.deadline) + ")";
    }
}
