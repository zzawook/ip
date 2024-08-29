package Dongji.components.parsers;

import java.time.format.DateTimeParseException;

import Dongji.components.DateTimeData;
import Dongji.components.commands.Command;
import Dongji.components.commands.DeadlineCommand;
import Dongji.components.commands.DeleteCommand;
import Dongji.components.commands.EventCommand;
import Dongji.components.commands.ListCommand;
import Dongji.components.commands.MarkCommand;
import Dongji.components.commands.TodoCommand;
import Dongji.components.commands.UnmarkCommand;
import Dongji.components.tasks.TaskList;
import Dongji.exceptions.DongjiParseException;
import Dongji.exceptions.DongjiUnknownInstructionException;

public class CommandParser {
    private TaskList taskList;

    public CommandParser(TaskList taskList) {
        this.taskList = taskList;
    }

    public Command parseToCommand(String commandString) throws DongjiUnknownInstructionException, DongjiParseException {
        String command = commandString.split(" ")[0];

        switch (command) {
        case "list":
            return new ListCommand(this.taskList);
        case "delete":
            int deleteIndex = this.parseIndex(commandString);
            return new DeleteCommand(this.taskList, deleteIndex);
        case "mark":
            int markIndex = this.parseIndex(commandString);
            return new MarkCommand(this.taskList, markIndex);
        case "unmark":
            int unmarkIndex = this.parseIndex(commandString);
            return new UnmarkCommand(this.taskList, unmarkIndex);
        case "todo":
            String taskName = this.extractTaskName(commandString);
            return new TodoCommand(this.taskList, taskName);
        case "event":
            if (!isCommandStringValidEvent(commandString)) {
                throw new DongjiParseException(
                        "Invalid command format for event. Please follow the format: event <task name> /from <yyyy-mm-dd [hhmm; optional]> /to <yyyy-mm-dd [hhmm; optional]>");
            }
            taskName = this.extractTaskName(commandString);

            DateTimeData eventStartDate;
            DateTimeData eventEndDate;

            eventStartDate = this.extractEventStartDate(commandString);
            eventEndDate = this.extractEventEndDate(commandString);

            if (isEventEndEarlierThanStart(eventStartDate, eventEndDate)) {
                throw new DongjiParseException("Event end date is earlier than start date. Please provide a valid dates");
            }

            return new EventCommand(this.taskList, taskName, eventStartDate, eventEndDate);
        case "deadline":
            if (!isCommandStringValidDeadline(commandString)) {
                throw new DongjiParseException(
                        "Invalid command format for deadline. Please follow the format: deadline <task name> /by <yyyy-mm-dd [hhmm; optional]>");
            }
            taskName = this.extractTaskName(commandString);
            DateTimeData deadlineDate = this.extractDeadline(commandString);
            return new DeadlineCommand(this.taskList, taskName, deadlineDate);
        default:
            return null;
        }
    }
    
    private boolean isEventEndEarlierThanStart(DateTimeData startDate, DateTimeData endDate) {
        return startDate.compareTo(endDate) > 0;
    }

    private boolean isCommandStringValidEvent(String commandString) {
        return (commandString.contains("/from") && commandString.contains("/to"));
    }

    private boolean isCommandStringValidDeadline(String commandString) {
        return commandString.contains("/by");
    }

    private int parseIndex(String commandString) {
        return Integer.parseInt(commandString.split(" ")[1]) - 1;
    }

    private String extractTaskName(String commandString) {
        String afterCommand = commandString.split(" ", 2)[1];
        return afterCommand.split(" /", 2)[0];
    }

    private DateTimeData extractDeadline(String commandString) throws DongjiParseException {
        String deadlineDateTimeString = commandString.split(" /by ", 2)[1];
        DateTimeData deadlineData;

        try {
            deadlineData = DateTimeParser.extractDateTime(deadlineDateTimeString);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for deadline. Please follow the format: deadline <task name> /by <yyyy-mm-dd [hhmm; optional]>");
        }

        return deadlineData;
    }

    private DateTimeData extractEventStartDate(String commandString) throws DongjiParseException {
        String afterFrom = commandString.split(" /from ", 2)[1];
        String beforeTo = afterFrom.split(" /to ", 2)[0];
        DateTimeData startDateData;

        try {
            startDateData = DateTimeParser.extractDateTime(beforeTo);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for event start date. Please follow the format: event <task name> /from <yyyy-mm-dd [hhmm; optional]> /to <yyyy-mm-dd [hhmm; optional]>");
        }

        return startDateData;
    }

    private DateTimeData extractEventEndDate(String commandString) throws DongjiParseException {
        String afterTo = commandString.split(" /to ", 2)[1];
        DateTimeData endDateData;

        try {
            endDateData = DateTimeParser.extractDateTime(afterTo);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for event end date. Please follow the format: event <task name> /from <yyyy-mm-dd [hhmm; optional]> /to <yyyy-mm-dd [hhmm; optional]>");
        }

        return endDateData;
    }
}
