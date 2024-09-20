package dongji.components.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.components.DateTimeData;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class DeadlineTest {

    private DateTimeData deadlineDateTime;

    @BeforeEach
    public void setup() {
        deadlineDateTime = DateTimeData.fromString("2023-12-31 2359");
    }

    @Test
    public void testDeadlineConstructor() throws DongjiEmptyTaskNameException {
        Deadline deadline = new Deadline("Submit assignment", deadlineDateTime);
        assertEquals("Submit assignment", deadline.getName());
        assertEquals(deadlineDateTime, deadline.getDeadline());
    }

    @Test
    public void testDeadlineConstructor_EmptyName() {
        assertThrows(DongjiEmptyTaskNameException.class, () -> {
            new Deadline("", deadlineDateTime);
        });
    }

    @Test
    public void testGetDeadline() throws DongjiEmptyTaskNameException {
        Deadline deadline = new Deadline("Submit assignment", deadlineDateTime);
        assertEquals(deadlineDateTime, deadline.getDeadline());
    }

    @Test
    public void testToString() throws DongjiEmptyTaskNameException {
        Deadline deadline = new Deadline("Submit assignment", deadlineDateTime);
        String expectedString = "[D][ ] Submit assignment (by: 2023-12-31 2359)";
        assertEquals(expectedString, deadline.toString());
    }
}