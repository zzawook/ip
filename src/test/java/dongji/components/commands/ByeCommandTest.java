package dongji.components.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dongji.Dongji;

public class ByeCommandTest {

    private Dongji dongji;
    private ByeCommand byeCommand;

    @BeforeEach
    public void setup() {
        dongji = new Dongji();
        byeCommand = new ByeCommand(dongji);
    }

    @Test
    public void testByeCommandExecution() {
        assertDoesNotThrow(() -> {
            String result = byeCommand.execute();
            assertEquals("Goodbye! Hope to see you again soon!", result);
        });
    }
}
