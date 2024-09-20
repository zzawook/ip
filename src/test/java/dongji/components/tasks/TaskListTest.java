package dongji.components.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.exceptions.DongjiEmptyTaskNameException;
import dongji.exceptions.DongjiIndexOutOfBoundException;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new Dongji().getTaskList();
        // Adding some tasks to the task list for testing
        try {
            taskList.add(new Todo("Task 1"));
            taskList.add(new Todo("Task 2"));
            taskList.add(new Todo("Task 3"));
        } catch (DongjiEmptyTaskNameException e) {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    @Test
    public void testAddTask() {
        int initialSize = taskList.size();
        try {
            taskList.add(new Todo("Task 4"));
            assertEquals(initialSize + 1, taskList.size());
            assertEquals("Task 4", taskList.get(taskList.size() - 1).getName());
        } catch (DongjiEmptyTaskNameException e) {
            System.out.println(e.getMessage());
            assert false;
        } catch (DongjiIndexOutOfBoundException e) {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    @Test
    public void testGetValidIndex() {
        assertDoesNotThrow(() -> {
            Task task = taskList.get(1);
            assertEquals("Task 2", task.getName());
        });
    }

    @Test
    public void testGetInvalidIndex() {
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.get(-1);
        });
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.get(3);
        });
    }

    @Test
    public void testDeleteTaskValidIndex() {
        assertDoesNotThrow(() -> {
            Task deletedTask = taskList.deleteTask(1);
            assertEquals("Task 2", deletedTask.getName());
            assertEquals(2, taskList.size());
        });
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.deleteTask(-1);
        });
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.deleteTask(3);
        });
    }

    @Test
    public void testMarkValidIndex() {
        assertDoesNotThrow(() -> {
            taskList.mark(1);
            Task task = taskList.get(1);
            assertEquals(true, task.isMarked());
        });
    }

    @Test
    public void testMarkInvalidIndex() {
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.mark(-1);
        });
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.mark(3);
        });
    }

    @Test
    public void testUnmarkValidIndex() {
        assertDoesNotThrow(() -> {
            taskList.mark(1);
            taskList.unmark(1);
            Task task = taskList.get(1);
            assertEquals(false, task.isMarked());
        });
    }

    @Test
    public void testUnmarkInvalidIndex() {
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.unmark(-1);
        });
        assertThrows(DongjiIndexOutOfBoundException.class, () -> {
            taskList.unmark(3);
        });
    }

    @Test
    public void testSize() {
        assertEquals(3, taskList.size());
        try {
            taskList.add(new Todo("Task 4"));
        } catch (DongjiEmptyTaskNameException e) {
            e.printStackTrace();
            assert false;
        }

        assertEquals(4, taskList.size());
    }
}
