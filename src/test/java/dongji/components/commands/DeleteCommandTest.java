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

public class DeleteCommandTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new Dongji().getTaskList();
        // Adding some tasks to the task list for testing
        try {
            while (taskList.size() > 0) {
                taskList.deleteTask(0);
            }

            taskList.add(new Todo("Task 1"));
            taskList.add(new Todo("Task 2"));
            taskList.add(new Todo("Task 3"));
        } catch (DongjiEmptyTaskNameException e) {
            System.out.println(e.getMessage());
            assert false;
        } catch (DongjiIndexOutOfBoundException e) {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    @Test
    public void testValidDeleteCommandExecution() {
        int initTaskListSize = taskList.size();
        DeleteCommand deleteCommand = new DeleteCommand(taskList, 1);

        assertDoesNotThrow(() -> {
            String result = deleteCommand.execute();
            assertEquals("Noted. I've removed this task:\n[T][ ] Task 2\nNow you have " + (initTaskListSize - 1)
                    + " tasks in the list.", result);
        });
    }

    @Test
    public void testDeleteCommandWithInvalidIndex() {
        DeleteCommand deleteCommand = new DeleteCommand(taskList, 10);

        assertDoesNotThrow(() -> {
            String result = deleteCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testDeleteCommandWithNegativeIndex() {
        DeleteCommand deleteCommand = new DeleteCommand(taskList, -1);

        assertDoesNotThrow(() -> {
            String result = deleteCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }

    @Test
    public void testDeleteCommandWithEmptyTaskList() {
        TaskList emptyTaskList = new TaskList();
        DeleteCommand deleteCommand = new DeleteCommand(emptyTaskList, 0);

        assertDoesNotThrow(() -> {
            String result = deleteCommand.execute();
            assertEquals("OOPS!!! Index out of bound! Please provide a valid index of task", result);
        });
    }
}
