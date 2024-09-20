package dongji.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateTimeDataTest {

    private DateTimeData dateTimeData1;
    private DateTimeData dateTimeData2;

    @BeforeEach
    public void setup() {
        dateTimeData1 = new DateTimeData(LocalDateTime.of(2023, 12, 31, 23, 59), true);
        dateTimeData2 = new DateTimeData(LocalDateTime.of(2023, 12, 31, 0, 0), true);
    }

    @Test
    public void testCompareTo() {
        assertTrue(dateTimeData1.compareTo(dateTimeData2) > 0);
        assertTrue(dateTimeData2.compareTo(dateTimeData1) < 0);
        assertTrue(dateTimeData1.compareTo(dateTimeData1) == 0);
    }

    @Test
    public void testFromString_ValidInput() {
        assertDoesNotThrow(() -> {
            DateTimeData dateTimeData = DateTimeData.fromString("2023-12-31 2359");
            assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), dateTimeData.getDateTime());
        });

        assertDoesNotThrow(() -> {
            DateTimeData dateTimeData = DateTimeData.fromString("2023-12-31");
            assertEquals(LocalDateTime.of(2023, 12, 31, 0, 0), dateTimeData.getDateTime());
        });
    }

    @Test
    public void testFromString_InvalidInput() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeData.fromString("invalid date");
        });
    }

    @Test
    public void testIsDateTime_ValidInput() {
        assertTrue(DateTimeData.isDateTime("2023-12-31T23:59"));
        assertTrue(DateTimeData.isDateTime("2023-12-31T00:00"));
    }

    @Test
    public void testIsDateTime_InvalidInput() {
        assertFalse(DateTimeData.isDateTime("invalid date"));
        assertFalse(DateTimeData.isDateTime("2023-12-31 23:59"));
    }

    // Add more tests for other methods if they exist
}