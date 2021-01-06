package school;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import school.views.LoginViewController;
import school.views.MainViewController;

import java.io.IOException;
import java.util.Scanner;

public class MainApp extends Application {
    private Stage primaryStage;

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    private BorderPane mainLayout;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Demo School Management System");

        initMainLayout();

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setMainLayout(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
    }

    /**
     * Initializes the main layout
     * */
    public void initMainLayout() {
        try {
            //Load main layout from fxml file.
            //Load main layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/MainView.fxml"));

            this.mainLayout = (BorderPane)loader.load();


            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);

            FXMLLoader loginLoader = new FXMLLoader();
            loginLoader.setLocation(MainApp.class.getResource("views/LoginView.fxml"));
            AnchorPane loginPane = (AnchorPane) loginLoader.load();
            Stage login = new Stage();
            Scene loginScene = new Scene(loginPane);
            login.setScene(loginScene);
            login.show();

            LoginViewController controller = loginLoader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            System.out.println("IO Exception occurred during loading login view");
            e.printStackTrace();
        }
    }


    /**
     * Returns the main stage
     * */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
