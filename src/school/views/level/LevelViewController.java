package school.views.level;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import school.MainApp;
import school.models.Level;
import school.models.LevelModel;
import school.utils.GUIUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LevelViewController implements Initializable {

    @FXML
    private TableView<Level> levelTable;
    @FXML
    private TableColumn<Level, Integer> idColumn;
    @FXML
    private TableColumn<Level, String> nameColumn;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;

    private MainApp mainApp;
    private LevelModel levelModel;


    /**
     * The constructor
     * The constructor is called before the initialize() method
     * */
    public LevelViewController() {
        this.levelModel = new LevelModel();
    }


    /**
     * Initializes the controller class. This method is automatically called
     * affter the fxml file has been loaded
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize the level table with two columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());
        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().getNameProperty());

        //Initialize leveltable with data
        initLevelTableData();

        //Clear level details
        this.showLevelDetails(null);

        //Listen for selection changes and show the level details was changed
        levelTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLevelDetails(newValue)
        );
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    private void initLevelTableData()
    {
        try {
            ObservableList<Level> levels = levelModel.getAll();
            levelTable.setItems(levels);

        } catch (SQLException e) {
            System.out.println("Sql Exception has occurred during initializing level table with levelModel");
            e.printStackTrace();
        }
    }

    private void showLevelDetails(Level level) {
        if (level != null)
        {
            idLabel.setText(Integer.toString(level.getID()));
            nameLabel.setText(level.getName());
        } else {
            idLabel.setText("");
            nameLabel.setText("");
        }
    }


    @FXML
    private void handleDeleteLevel() {
        int selectedIndex = levelTable.getSelectionModel().getSelectedIndex();
        Level level = levelTable.getSelectionModel().getSelectedItem();

        if(selectedIndex >= 0)
        {
            try {
                levelModel.delete(level);
                levelTable.getItems().remove(selectedIndex);

                GUIUtil.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage(),
                        "Information",
                        "Delete Success",
                        "Level : " + level.getName() + " has been deleted");

            } catch (SQLException e) {
                System.out.println("Sql Exception has occurred during initializing level table with levelModel");
                e.printStackTrace();

            }
        } else {
            GUIUtil.showAlert(Alert.AlertType.WARNING,
                    mainApp.getPrimaryStage(),
                    "No Selection",
                    " No Level Selected",
                    "Please Select a level in the table.");

        }

    }

    private boolean showLevelEditDialog(Level level) {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LevelViewController.class.getResource("LevelEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Level");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Set the person into the Controller.
            LevelEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLevel(level);

            //Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch(IOException e)
        {
            System.out.println("IO Exception occurred during loading level edit dialog");
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewLevel() {
        Level tempLevel = new Level();
        boolean isSaveClicked = this.showLevelEditDialog(tempLevel);
        if (isSaveClicked) {

            try {
                if (levelModel.add(tempLevel) == 1) {
                    tempLevel.setID( levelTable.getItems().get((levelTable.getItems().size() - 1)).getID() + 1) ;
                    levelTable.getItems().add(tempLevel);
                }

            } catch (SQLException e) {
                System.out.println("Sql exception has occurred during level inserting to database");
                e.printStackTrace();
            }

        }
    }

    /**
     * Called when the user clicks the edit button. Open a dialog to edit details for the selected level
     * */
    @FXML
    private void handleEditLevel() {
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        int selectedIndex = levelTable.getSelectionModel().getSelectedIndex();
        if (selectedLevel != null) {
            boolean isSaveClicked = showLevelEditDialog(selectedLevel);

            if (isSaveClicked) {
                try {
                    if (levelModel.update(selectedLevel) == 1) {
                        selectedLevel = levelModel.getByID(selectedLevel.getID());
                        levelTable.getItems().set(selectedIndex, selectedLevel);
                    }
                } catch (SQLException e) {
                    System.out.println("Sql exception has occurred during level inserting to database");
                    e.printStackTrace();
                }
            }
        } else {
            GUIUtil.showAlert(Alert.AlertType.WARNING,
                    mainApp.getPrimaryStage(),
                    "No Selection",
                    " No Level Selected",
                    "Please Select a level in the table.");
        }
    }
}
