package dongji.components.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dongji.components.DateTimeData;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class EventTest {

    @Test
    public void testEventWithoutNameThrowsException() {
        LocalDateTime dateEarlier = LocalDateTime.of(2024, 8, 30, 12, 0);
        LocalDateTime dateLater = LocalDateTime.of(2024, 8, 30, 14, 0);

        assertThrows(DongjiEmptyTaskNameException.class,
                () -> new Event("", new DateTimeData(dateEarlier, true), new DateTimeData(dateLater, true)),
                "Task name cannot be empty! Please provide a task name");
    }

    @Test
    public void testEventWithValidStartDateWithTimeAndEndDateWithTime() {
        LocalDateTime dateEarlier = LocalDateTime.of(2024, 8, 30, 12, 0);
        LocalDateTime dateLater = LocalDateTime.of(2024, 8, 30, 14, 0);

        Event event = assertDoesNotThrow(() -> new Event("Test Event", new DateTimeData(dateEarlier, true),
                new DateTimeData(dateLater, true)));

        assertNotNull(event);
    }

    @Test
    public void testEventWithValidStartDateWithoutTimeAndEndDateWithoutTime() {
        LocalDateTime dateEarlier = LocalDateTime.of(2024, 8, 30, 0, 0);
        LocalDateTime dateLater = LocalDateTime.of(2024, 8, 30, 0, 0);

        Event event = assertDoesNotThrow(() -> new Event("Test Event", new DateTimeData(dateEarlier, false),
                new DateTimeData(dateLater, false)));

        assertNotNull(event);
    }
}
