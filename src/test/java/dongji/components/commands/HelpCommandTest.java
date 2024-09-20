package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelpCommandTest {

    private HelpCommand helpCommand;

    @BeforeEach
    public void setup() {
        helpCommand = new HelpCommand();
    }

    @Test
    public void testHelpCommandExecution() {
        assertDoesNotThrow(() -> {
            String result = helpCommand.execute();
            String expected = "Here are the commands you can use:\n"
                + "1. todo <task name> - Adds a task to the task list\n"
                + "2. deadline <task name> /by <yyyy-mm-dd [hhmm; optional]> - "
                + "Adds a task with a deadline to the task list\n"
                + "3. event <task name> /from <yyyy-mm-dd [hhmm; optional]> /to <yyyy-mm-dd [hhmm; optional]> "
                + "- Adds a event task with a start and end date-time\n"
                + "4. recur <task name> <cron; * * * * *> - Adds a recurring task to the task list\n"
                + "5. list - Lists all tasks in the task list with task number\n"
                + "6. mark <task number> - Marks a task as done\n"
                + "7. unmark <task number> - Unmarks a task as not done\n"
                + "8. delete <task number> - Deletes a task from the task list\n"
                + "9. find <keyword> - Finds all tasks with the keyword in the task list\n"
                + "10. help - Shows the list of commands\n"
                + "11. bye - Exits the application";
            assertEquals(expected, result);
        });
    }
}
