package dongji.components.parsers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dongji.Dongji;
import dongji.components.commands.ByeCommand;
import dongji.components.commands.Command;
import dongji.components.commands.DeadlineCommand;
import dongji.components.commands.DeleteCommand;
import dongji.components.commands.EventCommand;
import dongji.components.commands.FindCommand;
import dongji.components.commands.HelpCommand;
import dongji.components.commands.ListCommand;
import dongji.components.commands.MarkCommand;
import dongji.components.commands.RecurringCommand;
import dongji.components.commands.TodoCommand;
import dongji.components.commands.UnmarkCommand;
import dongji.exceptions.DongjiParseException;
import dongji.exceptions.DongjiUnknownInstructionException;

public class CommandParserTest {
    static CommandParser commandParser;

    @BeforeAll
    public static void setup() {
        Dongji dongji = new Dongji();
        commandParser = new CommandParser(dongji);
    }

    @Test
    public void testParseListCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("list"));

        assertNotNull(command);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseAddTodoCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("todo test"));

        assertNotNull(command);
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void testParseAddDeadlineCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("deadline test /by 2024-08-30 1200"));

        assertNotNull(command);
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseAddDeadlineCommandWithoutTime() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("deadline test /by 2024-08-30"));

        assertNotNull(command);
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseAddDeadlineCommandWithoutTimeAndDateThrowsDongjiParseException() {
        assertThrows(DongjiParseException.class, () -> commandParser.parseToCommand("deadline test"));
    }

    @Test
    public void testParseAddDeadlineCommandWithInvalidDateTimeFormatThrowsDongjiParseException() {
        assertThrows(DongjiParseException.class,
                () -> commandParser.parseToCommand("deadline test /by 2024-08-30 12:00"));
        assertThrows(DongjiParseException.class, () -> commandParser.parseToCommand("deadline test /by 20240830 1200"));
    }

    @Test
    public void testParseAddEventCommand() {
        Command command = null;
        command = assertDoesNotThrow(
                () -> commandParser.parseToCommand("event test /from 2024-08-30 1200 /to 2024-08-30 1400"));

        assertNotNull(command);
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseAddEventCommandWithoutTime() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("event test /from 2024-08-30 /to 2024-08-30"));

        assertNotNull(command);
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseAddEventCommandWithoutTimeAndDateThrowsDongjiParseException() {
        assertThrows(DongjiParseException.class, () -> commandParser.parseToCommand("event test /from /to"));
    }

    @Test
    public void testParseAddEventCommandWithInvalidDateTimeFormatThrowsDongjiParseException() {
        assertThrows(DongjiParseException.class,
                () -> commandParser.parseToCommand("event test /from 2024-08-30 12:00 /to 2024-08-30 14:00"));
        assertThrows(DongjiParseException.class, () -> commandParser.parseToCommand("event test /from 20240830 1200 /to 20240830 1400"));
    }

    @Test
    public void testParseAddEventCommandWithEndDateBeforeStartDateThrowsDongjiParseException() {
        assertThrows(DongjiParseException.class,
                () -> commandParser.parseToCommand("event test /from 2024-08-30 1400 /to 2024-08-30 1200"));
    }

    @Test
    public void testParseRecurringCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("recur test /cron * * * * *"));

        assertNotNull(command);
        assertInstanceOf(RecurringCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("delete 1"));

        assertNotNull(command);
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("mark 1"));

        assertNotNull(command);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("unmark 1"));

        assertNotNull(command);
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseFindCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("find test"));

        assertNotNull(command);
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void testParseHelpCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("help"));

        assertNotNull(command);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void testParseByeCommand() {
        Command command = null;
        command = assertDoesNotThrow(() -> commandParser.parseToCommand("bye"));

        assertNotNull(command);
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(DongjiUnknownInstructionException.class, () -> commandParser.parseToCommand("invalid"));
    }
}
