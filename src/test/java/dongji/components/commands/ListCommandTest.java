package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.components.DateTimeData;
import dongji.components.tasks.Deadline;
import dongji.components.tasks.Event;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;

public class ListCommandTest {
    private ListCommand listCommand;
    private TaskList taskList;

    @BeforeEach
    public void setup() {
        this.taskList = new TaskList();
        this.listCommand = new ListCommand(taskList);
    }

    @Test
    public void testExecuteWithItems() {
        LocalDateTime dateEarlier = LocalDateTime.of(2024, 8, 30, 12, 0);
        LocalDateTime dateLater = LocalDateTime.of(2024, 8, 30, 14, 0);

        Todo todo;
        Event event;
        Deadline deadline;
        try {
            todo = new Todo("Test TODO");
            event = new Event("Test Event", new DateTimeData(dateEarlier, true), new DateTimeData(dateLater, true));
            deadline = new Deadline("Test Deadline", new DateTimeData(dateEarlier, true));
        } catch (Exception e) {
            System.out.println("Error creating tasks");
            return;
        }

        this.taskList.add(todo);
        this.taskList.add(event);
        this.taskList.add(deadline);

        assertEquals(
                "1. [T][ ] Test TODO\n2. [E][ ] Test Event (from: 2024-08-30 1200 to: 2024-08-30 1400)"
                        + "\n3. [D][ ] Test Deadline (by: 2024-08-30 1200)",
                this.listCommand.execute());
    }

    @Test
    public void testExecuteWithNoItems() {
        assertEquals("No tasks in the list!", this.listCommand.execute());
    }
}
