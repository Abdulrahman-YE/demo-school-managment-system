package school.views.teacher;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import school.MainApp;
import school.models.Teacher;
import school.models.TeacherModel;
import school.utils.GUIUtil;
import school.views.level.LevelEditDialogController;
import school.views.level.LevelViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    private TeacherModel teacherModel;
    private MainApp mainApp;

    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, Integer> idColumn;
    @FXML
    private TableColumn<Teacher, String> nameColumn;
    @FXML
    private TableColumn<Teacher, String> addressColumn;
    @FXML
    private TableColumn<Teacher, String> phoneColumn;
    @FXML
    private TableColumn<Teacher, String> genderColumn;
    @FXML
    private TableColumn<Teacher, LocalDate> dobColumn;
    @FXML
    private  TableColumn<Teacher, Integer> salaryColumn;


    public TeacherViewController() {
        teacherModel = new TeacherModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Initialize the teacher table columns
        idColumn.setCellValueFactory( cellData -> cellData.getValue().getIDProperty().asObject());
        nameColumn.setCellValueFactory( cellData -> cellData.getValue().getNameProperty());
        addressColumn.setCellValueFactory( cellData -> cellData.getValue().getAddressProperty()  );
        phoneColumn.setCellValueFactory( cellData -> cellData.getValue().getAddressProperty());
        genderColumn.setCellValueFactory( cellData -> cellData.getValue().getGenderProperty());
        dobColumn.setCellValueFactory( cellData -> cellData.getValue().getDOBProperty());
        salaryColumn.setCellValueFactory( cellData -> cellData.getValue().getSalaryProperty().asObject());

        //initialize the teacher table with data
        initTeacherTable();

    }

    private void initTeacherTable() {
        try {
            ObservableList<Teacher> teachers = teacherModel.getAll();
            teacherTable.setItems(teachers);

        } catch(SQLException e) {
            System.out.println("SQL Exception has occurred during initialing the table with data");
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    private boolean showTeacherEditDialog(Teacher teacher) {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TeacherEditDialogController.class.getResource("TeacherEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Teacher");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Set the person into the Controller.
            TeacherEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTeacher(teacher);

            //Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch(IOException e)
        {
            System.out.println("IO Exception occurred during loading teacher edit dialog");
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void handleDeleteTeacher() {
        int selectedIndex = teacherTable.getSelectionModel().getSelectedIndex();
        Teacher teacher = teacherTable.getSelectionModel().getSelectedItem();

        if(selectedIndex >= 0)
        {
            try {
                teacherModel.delete(teacher);
                teacherTable.getItems().remove(selectedIndex);

                GUIUtil.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage(),
                        "Information",
                        "Delete Success",
                        "Teacher : " + teacher.getName() + " has been deleted");

            } catch (SQLException e) {
                System.out.println("Sql Exception has occurred during initializing teacher table with teacherModel");
                e.printStackTrace();

            }
        } else {
            GUIUtil.showAlert(Alert.AlertType.WARNING,
                    mainApp.getPrimaryStage(),
                    "No Selection",
                    " No Teacher Selected",
                    "Please Select a teacher in the table.");

        }

    }

    @FXML
    private void handleNewTeacher() {
        Teacher tempTeacher = new Teacher();
        boolean isSaveClicked = this.showTeacherEditDialog(tempTeacher);
        if (isSaveClicked) {

            try {
                if (teacherModel.add(tempTeacher) == 1) {
                    initTeacherTable();
                }

            } catch (SQLException e) {
                System.out.println("Sql exception has occurred during teacher inserting to database");
                e.printStackTrace();
            }

        }
    }

    /**
     * Called when the user clicks the edit button. Open a dialog to edit details for the selected teacher
     * */
    @FXML
    private void handleEditTeacher() {
        Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        int selectedIndex = teacherTable.getSelectionModel().getSelectedIndex();
        if (selectedTeacher != null) {
            boolean isSaveClicked = showTeacherEditDialog(selectedTeacher);

            if (isSaveClicked) {
                try {
                    if (teacherModel.update(selectedTeacher) == 1) {
                        selectedTeacher = teacherModel.getByID(selectedTeacher.getID());
                        teacherTable.getItems().set(selectedIndex, selectedTeacher);
                    }
                } catch (SQLException e) {
                    System.out.println("Sql exception has occurred during teacher editting to database");
                    e.printStackTrace();
                }
            }
        } else {
            GUIUtil.showAlert(Alert.AlertType.WARNING,
                    mainApp.getPrimaryStage(),
                    "No Selection",
                    " No Teacher Selected",
                    "Please Select a teacher in the table.");
        }
    }
}
