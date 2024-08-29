package components.tasks;

import components.DateTimeData;
import exceptions.DongjiEmptyTaskNameException;

public class Deadline extends Task {

    private DateTimeData deadline;

    public Deadline(String name, DateTimeData deadline) throws DongjiEmptyTaskNameException{
        super(name);
        this.deadline = deadline;
    }

    public DateTimeData getDeadline() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeData.formatDate(this.deadline) + ")";
    }
}
