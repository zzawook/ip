package dongji.components.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.DateTimeData;
import dongji.components.tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class EventCommandTest {
    
    static TaskList taskList;
    static LocalDateTime earlierLDT;
    static LocalDateTime laterLDT;
    static DateTimeData dateEarlier;
    static DateTimeData dateLater;

    @BeforeAll
    public static void setup() {
        taskList = new Dongji().getTaskList();
        earlierLDT = LocalDateTime.of(2024, 8, 30, 12, 0);
        laterLDT = LocalDateTime.of(2024, 8, 30, 14, 0);
        dateEarlier = new DateTimeData(earlierLDT, true);
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
