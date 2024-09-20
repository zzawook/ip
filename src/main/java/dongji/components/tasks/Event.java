package dongji.components.tasks;

import dongji.components.DateTimeData;
import dongji.exceptions.DongjiEmptyTaskNameException;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private DateTimeData eventStart;
    private DateTimeData eventEnd;

    /**
     * Constructor for Event
     * 
     * @param name
     * @param eventStart
     * @param eventEnd
     * @throws DongjiEmptyTaskNameException
     */
    public Event(String name, DateTimeData eventStart, DateTimeData eventEnd) throws DongjiEmptyTaskNameException {
        super(name);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Returns the DateTimeDate object that represents start date of the event.
     * 
     * @return DateTimeData
     */
    public DateTimeData getEventStart() {
        return this.eventStart;
    }

    /**
     * Returns the DateTimeDate object that represents end date of the event.
     * 
     * @return DateTimeData
     */
    public DateTimeData getEventEnd() {
        return this.eventEnd;
    }

    /**
     * Returns the string representation of the Event object.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeData.formatDate(this.eventStart) + " to: "
                + DateTimeData.formatDate(this.eventEnd) + ")";
    }
}
