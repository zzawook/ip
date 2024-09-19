package dongji.components.commands;

import dongji.Dongji;
import javafx.application.Platform;

/**
 * Command to exit the application
 */
public class ByeCommand implements Command {

    private Dongji dongji;

    public ByeCommand(Dongji dongji) {
        this.dongji = dongji;
    }

    @Override
    public String execute() {
        this.dongji.terminate();
        Platform.exit();

        return "Goodbye! Hope to see you again soon!";
    }
}
