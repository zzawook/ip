package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.tasks.TaskList;

public class TodoCommandTest {

    private static TaskList taskList;

    @BeforeAll
    public static void setup() {
        taskList = new Dongji().getTaskList();
    }

    @Test
    public void testValidTodoCommandExecution() {
        TodoCommand todoCommand = new TodoCommand(taskList, "test");
        int initTaskListSize = taskList.size();
        assertDoesNotThrow(() -> todoCommand.execute());

        assertEquals(taskList.size(), initTaskListSize + 1);
    }

    @Test
    public void testEmptyTodoCommandExecutionCatchesException() {
        TodoCommand todoCommand = new TodoCommand(taskList, "");
        assertEquals("OOPS!!! Task name cannot be empty! Please provide a task name", todoCommand.execute());
    }
}
