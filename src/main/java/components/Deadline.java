package components;

import exceptions.DongjiEmptyTaskNameException;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) throws DongjiEmptyTaskNameException{
        super(name);
        this.deadline = deadline;
    }

    public static Deadline fromInputString(String input) throws DongjiEmptyTaskNameException {
        if (! checkDeadlineInputValid(input)) {
            throw new DongjiEmptyTaskNameException("Deadline input is invalid! Please provide a deadline for the task");
        }

        return parseInputToDeadline(input);
    }

    private static boolean checkDeadlineInputValid(String input) {
        return input.contains("/by");
    }

    private static Deadline parseInputToDeadline(String input) throws DongjiEmptyTaskNameException{
        String[] splitInput = input.split("/by");

        String deadlineName = splitInput[0].trim();
        String deadline = splitInput[1].trim();

        return new Deadline(deadlineName, deadline);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
