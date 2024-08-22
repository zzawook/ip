package components;

import exceptions.DongjiEmptyTaskNameException;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) throws DongjiEmptyTaskNameException{
        super(name);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
