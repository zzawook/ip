package components;
import components.commands.Command;
import components.commands.DeadlineCommand;
import components.commands.DeleteCommand;
import components.commands.EventCommand;
import components.commands.ListCommand;
import components.commands.MarkCommand;
import components.commands.TodoCommand;
import components.commands.UnmarkCommand;
import components.tasks.TaskList;
import exceptions.DongjiUnknownInstructionException;

public class Parser {
    private TaskList taskList;
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public Command parseToCommand(String commandString) throws DongjiUnknownInstructionException {
        String command = commandString.split(" ")[0];

        switch(command) {
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
                taskName = this.extractTaskName(commandString);
                String eventStartDate = this.extractEventStartDate(commandString);
                String eventEndDate = this.extractEventEndDate(commandString);
                return new EventCommand(this.taskList, taskName, eventStartDate, eventEndDate);
            case "deadline":
                taskName = this.extractTaskName(commandString);
                String deadlineDate = this.extractDeadline(commandString);
                return new DeadlineCommand(this.taskList, taskName, deadlineDate);
            default:
                return null;
        }
    }

    private int parseIndex(String commandString) {
        return Integer.parseInt(commandString.split(" ")[1]) - 1;
    }

    private String extractTaskName(String commandString) {
        return commandString.split(" ")[1];
    }

    private String extractDeadline(String commandString) {
        return commandString.split(" /by ", 2)[1];
    }

    private String extractEventStartDate(String commandString) {
        String afterFrom = commandString.split(" /from ", 2)[1];
        return afterFrom.split(" /to ", 2)[0];
    }

    private String extractEventEndDate(String commandString) {
        return commandString.split(" /to ", 2)[1];
    }
}
