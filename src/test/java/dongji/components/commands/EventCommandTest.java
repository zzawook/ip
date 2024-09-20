package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.DateTimeData;
import dongji.components.tasks.TaskList;

public class EventCommandTest {

    private static TaskList taskList;
    private static LocalDateTime earlierLdt;
    private static LocalDateTime laterLdt;
    private static DateTimeData dateEarlier;
    private static DateTimeData dateLater;

    @BeforeAll
    public static void setup() {
        taskList = new Dongji().getTaskList();
        earlierLdt = LocalDateTime.of(2024, 8, 30, 12, 0);
        laterLdt = LocalDateTime.of(2024, 8, 30, 14, 0);
        dateEarlier = new DateTimeData(earlierLdt, true);
        dateLater = new DateTimeData(laterLdt, true);
    }

    @Test
    public void testValidEventCommandExecution() {
        EventCommand eventCommand = new EventCommand(taskList, "test", dateEarlier, dateLater);
        int initTaskListSize = taskList.size();
        assertDoesNotThrow(() -> eventCommand.execute());

        assertEquals(taskList.size(), initTaskListSize + 1);
    }

    @Test
    public void testEmptyEventCommandExecutionCatchesException() {
        EventCommand eventCommand = new EventCommand(taskList, "", dateEarlier, dateLater);
        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", eventCommand.execute());
    }
}
