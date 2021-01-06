package school.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import school.MainApp;
import school.views.level.LevelViewController;
import school.views.teacher.TeacherViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    MainApp mainApp;
    @FXML
    private void handleLevelClicked() {

        FXMLLoader loader = this.changeScene("level/LevelView.fxml");
        if (loader != null) {
            LevelViewController controller = loader.getController();
            controller.setMainApp(mainApp);
        } else {
            System.out.println("FXMLLoader is null. Please fix the path");
        }


    }

    @FXML
    private void handleTeacherClicked() {
        FXMLLoader loader = this.changeScene("teacher/TeacherView.fxml");
        if (loader != null) {
            TeacherViewController controller = loader.getController();
            controller.setMainApp(mainApp);
        } else {
            System.out.println("FXMLLoader is null. Please fix the path");
        }


    }

    private FXMLLoader changeScene(String path) {
        try {
            //Load level view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainViewController.class.getResource(path));
            AnchorPane view = (AnchorPane) loader.load();

            //Set level view into the center of the main layout
            mainApp.getMainLayout().setCenter(view);

            return  loader;

        } catch (IOException e) {
            System.out.println("IO Exception occurred during loading level view");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
