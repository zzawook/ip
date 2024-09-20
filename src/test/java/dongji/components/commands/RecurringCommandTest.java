package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.components.tasks.TaskList;

public class RecurringCommandTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
    }

    @Test
    public void testValidRecurringCommandExecution() {
        RecurringCommand recurringCommand = new RecurringCommand(taskList, "Test Recurring Task", "* * * * *");

        assertDoesNotThrow(() -> {
            String result = recurringCommand.execute();
            assertEquals("added Test Recurring Task", result);
            assertEquals(1, taskList.size());
            assertEquals("Test Recurring Task", taskList.get(0).getName());
        });
    }

    @Test
    public void testRecurringCommandWithEmptyTaskName() {
        RecurringCommand recurringCommand = new RecurringCommand(taskList, "", "* * * * *");

        assertDoesNotThrow(() -> {
            String result = recurringCommand.execute();
            assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", result);
            assertEquals(0, taskList.size());
        });
    }

    @Test
    public void testRecurringCommandWithInvalidCronExpression() {
        RecurringCommand recurringCommand = new RecurringCommand(taskList, "Test Recurring Task", "invalid cron");

        assertDoesNotThrow(() -> {
            String result = recurringCommand.execute();
            assertEquals("OOPS!!! Invalid cron expression", result);
        });
    }
}
