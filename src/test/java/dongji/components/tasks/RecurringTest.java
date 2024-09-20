package dongji.components.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.exceptions.DongjiEmptyTaskNameException;
import dongji.exceptions.DongjiParseException;

public class RecurringTest {

    @BeforeEach
    public void setup() {
        // Any setup if needed
    }

    @Test
    public void testRecurringConstructorValidCron() throws DongjiEmptyTaskNameException, DongjiParseException {
        Recurring recurring = new Recurring("Daily standup", "* * * * *");
        assertEquals("Daily standup", recurring.getName());
        assertEquals("* * * * *", recurring.getCron());
    }

    @Test
    public void testRecurringConstructorInvalidCron() {
        assertThrows(DongjiParseException.class, () -> {
            new Recurring("Daily standup", "invalid cron");
        });
    }

    @Test
    public void testRecurringConstructorEmptyName() {
        assertThrows(DongjiEmptyTaskNameException.class, () -> {
            new Recurring("", "* * * * *");
        });
    }

    @Test
    public void testGetCron() throws DongjiEmptyTaskNameException, DongjiParseException {
        Recurring recurring = new Recurring("Daily standup", "* * * * *");
        assertEquals("* * * * *", recurring.getCron());
    }
}
