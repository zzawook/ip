package dongji.components.ui;

import dongji.Dongji;
import dongji.Main;
import dongji.components.ui.javafx.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ui {
    public static void startUi(Stage stage, Dongji dongji) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDongji(dongji); // inject the Dongji instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
