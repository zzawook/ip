package Dongji.components.tasks;

import Dongji.components.DateTimeData;
import Dongji.exceptions.DongjiEmptyTaskNameException;

public class Event extends Task {

    private DateTimeData eventStart;
    private DateTimeData eventEnd;

    public Event(String name, DateTimeData eventStart, DateTimeData eventEnd) throws DongjiEmptyTaskNameException{
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

    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeData.formatDate(this.eventStart) + " to: " + DateTimeData.formatDate(this.eventEnd) + ")";
    }
}
