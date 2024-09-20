package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class MarkCommandTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new Dongji().getTaskList();
        // Adding some tasks to the task list for testing
        try {
            taskList.add(new Todo("Read book"));
            taskList.add(new Todo("Write code"));
            taskList.add(new Todo("Read documentation"));
        } catch (DongjiEmptyTaskNameException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testValidMarkCommandExecution() {
        MarkCommand markCommand = new MarkCommand(taskList, 1);

        assertDoesNotThrow(() -> {
            String result = markCommand.execute();
            assertEquals("Nice! I've marked this task as done:", result);
            assertEquals(true, taskList.get(1).isMarked());
        });
    }

    @Test
    public void testMarkCommandWithInvalidIndex() {
        MarkCommand markCommand = new MarkCommand(taskList, 10);

        assertDoesNotThrow(() -> {
            String result = markCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testMarkCommandWithNegativeIndex() {
        MarkCommand markCommand = new MarkCommand(taskList, -1);

        assertDoesNotThrow(() -> {
            String result = markCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testMarkCommandWithEmptyTaskList() {
        TaskList emptyTaskList = new TaskList();
        MarkCommand markCommand = new MarkCommand(emptyTaskList, 0);

        assertDoesNotThrow(() -> {
            String result = markCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }
}
