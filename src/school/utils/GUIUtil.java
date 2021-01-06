package school.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class GUIUtil {

    public static void showAlert(Alert.AlertType alertType, Stage owner, String title, String headerText, String contentText)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.initOwner(owner);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
