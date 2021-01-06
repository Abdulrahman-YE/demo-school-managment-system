package school.views.teacher;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import school.models.Teacher;
import school.utils.DateUtil;
import school.utils.GUIUtil;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TeacherEditDialogController implements Initializable {

    private Teacher teacher;

    private ToggleGroup genderGroup;

    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField salaryField;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private RadioButton maleRButton;
    @FXML
    private RadioButton femaleRButton;


    private Stage dialogStage;
    private boolean saveClicked = false;


    /**
     * Set the stage of this dialog
     * @param dialogStage
     * */
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the Teacher to be edited in the dialog
     *
     * @param teacher
     * */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        nameField.setText(teacher.getName());
        phoneField.setText(teacher.getPhone());
        addressField.setText(teacher.getAddress());
        dobPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return DateUtil.format(date);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String s) {
                if (s == null || s.trim().isEmpty()) {
                    return null;
                }
                return DateUtil.parse(s);
            }
        });

        dobPicker.setValue(teacher.getDOB());

        if (teacher.getGender() != null) {
            if (teacher.getGender().equals("male")) {
                maleRButton.setSelected(true);
            } else {
                femaleRButton.setSelected(true);
            }
        } else {
            maleRButton.setSelected(false);
            femaleRButton.setSelected(false);
        }

        salaryField.setText(Integer.toString(teacher.getSalary()));
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
            teacher.setName(nameField.getText());
            teacher.setAddress(addressField.getText());
            teacher.setPhone(phoneField.getText());
            RadioButton selected = (RadioButton) genderGroup.getSelectedToggle();
            String toggleGroupValue = selected.getText().toLowerCase();
            System.out.println(toggleGroupValue);
            teacher.setGender(toggleGroupValue);
            teacher.setSalary(Integer.parseInt( salaryField.getText()));
            teacher.setDOB(dobPicker.getValue());
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
        if (phoneField.getText() == null || phoneField.getText().isEmpty()) {
            errorMessage += "No valid phone number\n";
        }
        if (addressField.getText() == null || addressField.getText().isEmpty()) {
            errorMessage += "No valid address\n";
        }
        if (salaryField.getText() == null || salaryField.getText().isEmpty()) {
            errorMessage += "No valid salary\n";
        }
        if (genderGroup.selectedToggleProperty().getName() == null) {
            errorMessage += "No valid name\n";
        }
        if (dobPicker.getValue() == null ) {
            errorMessage += "No valid date of birth\n";
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderGroup = new ToggleGroup();
        maleRButton.setToggleGroup(genderGroup);
        femaleRButton.setToggleGroup(genderGroup);



    }
}
