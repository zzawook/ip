package components;

import exceptions.DongjiEmptyTaskNameException;

public class Event extends Task {

    private String eventStart;
    private String eventEnd;

    public Event(String name, String eventStart, String eventEnd) throws DongjiEmptyTaskNameException{
        super(name);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStart + " to: " + this.eventEnd + ")";
    }
}
