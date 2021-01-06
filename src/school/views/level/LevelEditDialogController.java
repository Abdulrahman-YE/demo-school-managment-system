package school.views.level;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import school.models.Level;
import school.utils.GUIUtil;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Dialog to edit details of a level
 *
 * */
public class LevelEditDialogController implements Initializable
{
    private Integer id;
    @FXML
    private TextField nameField;

    private Stage dialogStage;
    private Level level;
    private boolean saveClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Set the stage of this dialog
     * @param dialogStage
     * */
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the level to be edited in the dialog
     *
     * @param level
     * */
    public void setLevel(Level level) {
        this.level = level;
        id = level.getID();
        nameField.setText(level.getName());
    }

    /**
     * Returns true if the user clicked OK, false otherwise
     * @return
     * */
    public boolean isSaveClicked() {
        return saveClicked;
    }

    /**
     * Called when the user clicks ok
     * */
    @FXML
    private void handleSave() {
        if (isInputValid()) {
            level.setName(nameField.getText());
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user click cancel
     * */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
    Validate the user input in the text field
    * @return true if the input is valid
    */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().isEmpty()) {
            errorMessage += "No valid name\n";
        }
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            GUIUtil.showAlert(Alert.AlertType.ERROR,
                    dialogStage,
                    "Invalid Fields",
                    "Please correct invalid fields",
                    errorMessage);

            return false;
        }
    }
}
