package dongji;

import dongji.components.ui.CommandLine;
import dongji.components.ui.javafx.UiMain;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        launchJavaFxApplication(args);
    }

    private static void launchJavaFxApplication(String[] args) {
        Application.launch(UiMain.class, args);
    }

    private static void launchCommandLineApplication(String[] args) {
        CommandLine ui = new CommandLine();
        ui.launch();
    }
}
