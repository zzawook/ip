package dongji.components.ui.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        this.clipAndSetImage(img);
    }

    private void clipAndSetImage(Image img) {
        displayPicture.setImage(img);
        displayPicture.setImage(this.clipImage(img));
    }

    private WritableImage clipImage(Image img) {
        assert img != null : "Image should not be null";

        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(displayPicture.getFitWidth());
        clip.setArcHeight(displayPicture.getFitHeight());
        displayPicture.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        displayPicture.setImage(img);
        WritableImage result = displayPicture.snapshot(parameters, null);

        displayPicture.setClip(null);

        return result;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void formatDialogBox(boolean isUser) {
        if (isUser) {
            stackPane.setPadding(new Insets(10, 5, 0, 10));
            setStyle("-fx-background-color: #dddddd; -fx-background-radius: 10px; -fx-padding: 0 10 0 10;");
        } else {
            stackPane.setPadding(new Insets(10, 15, 0, 5));
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            Collections.reverse(tmp);
            getChildren().setAll(tmp);
            setAlignment(Pos.TOP_LEFT);
            setStyle("-fx-background-color: #aaaaaa; -fx-background-radius: 10px; -fx-padding: 0 10 0 10;");
        }

    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.formatDialogBox(true);
        return dialogBox;
    }

    public static DialogBox getDongjiDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.formatDialogBox(false);

        if (text.contains("OOPS!!!")) {
            dialogBox.setStyle("-fx-background-color: #ff0000; -fx-background-radius: 10px; -fx-padding: 0 10 0 10;");
        }
        return dialogBox;
    }
}
