package components.tasks;

import exceptions.DongjiEmptyTaskNameException;
import exceptions.DongjiParseException;

public class Event extends Task {

    private String eventStart;
    private String eventEnd;

    public Event(String name, String eventStart, String eventEnd) throws DongjiEmptyTaskNameException{
        super(name);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public static Event fromInputString(String input) throws DongjiParseException, DongjiEmptyTaskNameException {
        if (!checkEventInputValid(input)) {
            throw new DongjiParseException("Event input is invalid! Please provide a valid event input");
        }

        return parseInputToEvent(input);
    }

    private static boolean checkEventInputValid(String input) {
        return input.contains("/from") && input.contains("/to");
    }

    private static Event parseInputToEvent(String input) throws DongjiEmptyTaskNameException{
        String[] splitInput = input.split("/from");

        String eventName = splitInput[0].trim();

        String[] splitDates = splitInput[1].split("/to");
        String eventStart = splitDates[0].trim();
        String eventEnd = splitDates[1].trim();

        return new Event(eventName, eventStart, eventEnd);
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStart + " to: " + this.eventEnd + ")";
    }
}
