package dongji.components.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.DateTimeData;
import dongji.components.tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineCommandTest {
    
    static TaskList taskList;
    static DateTimeData due;
    static DateTimeData dueWithoutTime;

    @BeforeAll
    public static void setup() {
        taskList = new Dongji().getTaskList();
        LocalDateTime dueLDT = LocalDateTime.of(2024, 8, 30, 12, 0);
        LocalDateTime dueLDTWithOutTime = LocalDateTime.of(2024, 8, 30, 0, 0);
        due = new DateTimeData(dueLDT, true);
        dueWithoutTime = new DateTimeData(dueLDTWithOutTime, false);
    }

    @Test
    public void testValidDeadlineCommandExecution() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "test", due);
        int initTaskListSize = taskList.size();
        assertDoesNotThrow(() -> deadlineCommand.execute());

        assertEquals(taskList.size(), initTaskListSize + 1);
    }

    @Test
    public void testEmptyDeadlineCommandExecutionCatchesException() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(taskList, "", due);
        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", deadlineCommand.execute());
    }

}
