package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;
import dongji.exceptions.DongjiIndexOutOfBoundException;

public class UnmarkCommandTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new Dongji().getTaskList();
        // Adding some tasks to the task list for testing
        try {
            taskList.add(new Todo("Read book"));
            taskList.add(new Todo("Write code"));
            taskList.add(new Todo("Read documentation"));

            taskList.mark(0);
            taskList.mark(1);
            taskList.mark(2);
        } catch (DongjiEmptyTaskNameException e) {
            e.printStackTrace();
            assert false;
        } catch (DongjiIndexOutOfBoundException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testValidUnmarkCommandExecution() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, 1);

        assertDoesNotThrow(() -> {
            String result = unmarkCommand.execute();
            assertEquals("Okay, I've marked this task as not done yet", result);
            assertEquals(false, taskList.get(1).isMarked());
        });
    }

    @Test
    public void testUnmarkCommandWithInvalidIndex() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, 10);

        assertDoesNotThrow(() -> {
            String result = unmarkCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testUnmarkCommandWithNegativeIndex() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, -1);

        assertDoesNotThrow(() -> {
            String result = unmarkCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testUnmarkCommandWithEmptyTaskList() {
        TaskList emptyTaskList = new TaskList();
        UnmarkCommand unmarkCommand = new UnmarkCommand(emptyTaskList, 0);

        assertDoesNotThrow(() -> {
            String result = unmarkCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }
}
