package dongji;

import dongji.components.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point to the application. 
 * Initializes the backend application Dongji, and starts the user interface.
 */
public class Main extends Application {

    private Dongji dongji;

    /**
     * Entry point to the application.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        this.dongji = new Dongji();
        Ui.startUi(stage, this.dongji);
    }

    @Override
    public void stop() {
        dongji.terminate();
    }
}
