package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.DateTimeData;
import dongji.components.tasks.TaskList;

public class DeadlineCommandTest {

    private static TaskList taskList;
    private static DateTimeData due;
    private static DateTimeData dueWithoutTime;

    @BeforeAll
    public static void setup() {
        taskList = new Dongji().getTaskList();
        LocalDateTime dueLdt = LocalDateTime.of(2024, 8, 30, 12, 0);
        LocalDateTime dueLdtWithOutTime = LocalDateTime.of(2024, 8, 30, 0, 0);
        due = new DateTimeData(dueLdt, true);
        dueWithoutTime = new DateTimeData(dueLdtWithOutTime, false);
    }

    @Test
    public void testValidDeadlineCommandExecution() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "test", due);
        DeadlineCommand deadlineCommandWithoutTime = new DeadlineCommand(taskList, "test", dueWithoutTime);
        int initTaskListSize = taskList.size();

        assertDoesNotThrow(() -> deadlineCommand.execute());
        assertEquals(taskList.size(), initTaskListSize + 1);

        assertDoesNotThrow(() -> deadlineCommandWithoutTime.execute());
        assertEquals(taskList.size(), initTaskListSize + 2);
    }

    @Test
    public void testEmptyDeadlineCommandExecutionCatchesException() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "", due);

        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", deadlineCommand.execute());
    }

    @Test
    public void testEmptyDeadlineCommandExecutionWithoutTimeCatchesException() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "", dueWithoutTime);

        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", deadlineCommand.execute());
    }

    @Test
    public void testDeadlineCommandWithWhitespaceTaskName() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "   ", due);
        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", deadlineCommand.execute());
    }
}
