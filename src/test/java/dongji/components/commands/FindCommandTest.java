package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class FindCommandTest {

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
    public void testFindCommandWithMatchingKeyword() {
        FindCommand findCommand = new FindCommand(taskList, "Read");

        assertDoesNotThrow(() -> {
            String result = findCommand.execute();
            System.out.println(result);
            String expected = "\n1. [T][ ] Read book\n2. [T][ ] Read documentation\n";
            assertEquals(expected, result);
        });
    }

    @Test
    public void testFindCommandWithNonMatchingKeyword() {
        FindCommand findCommand = new FindCommand(taskList, "Sleep");

        assertDoesNotThrow(() -> {
            String result = findCommand.execute();
            String expected = "\n";
            assertEquals(expected, result);
        });
    }

    @Test
    public void testFindCommandWithEmptyKeyword() {
        FindCommand findCommand = new FindCommand(taskList, "");

        assertDoesNotThrow(() -> {
            String result = findCommand.execute();
            String expected = "\n1. [T][ ] Read book\n2. [T][ ] Write code\n3. [T][ ] Read documentation\n";
            assertEquals(expected, result);
        });
    }

    @Test
    public void testFindCommandWithNullKeyword() {
        FindCommand findCommand = new FindCommand(taskList, null);

        assertDoesNotThrow(() -> {
            String result = findCommand.execute();
            String expected = "\n1. [T][ ] Read book\n2. [T][ ] Write code\n3. [T][ ] Read documentation\n";
            assertEquals(expected, result);
        });
    }

    @Test
    public void testFindCommandWithEmptyTaskList() {
        TaskList emptyTaskList = new TaskList();
        FindCommand findCommand = new FindCommand(emptyTaskList, "Read");

        assertDoesNotThrow(() -> {
            String result = findCommand.execute();
            String expected = "\n";
            assertEquals(expected, result);
        });
    }
}
