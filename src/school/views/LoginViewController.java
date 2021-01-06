package school.views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import school.MainApp;
import school.models.Account;
import school.models.AccountModel;
import school.utils.GUIUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    Account account;
    AccountModel accountModel;
    MainApp mainApp;



    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    public LoginViewController() {
        account = new Account();
        accountModel = new AccountModel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClose() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleLogin() {

        if (isInputValide()) {

            try {
                account.setUsername(userField.getText());
                account.setPassword(passwordField.getText());

                if(accountModel.checkAccount(account))
                {
                    //Load main layout from fxml file.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LoginViewController.class.getResource("MainView.fxml"));

                     mainApp.setMainLayout((BorderPane)loader.load());

                    //show the scene containing the main layout
                    Stage primaryStage = mainApp.getPrimaryStage();
                    Scene scene = new Scene(mainApp.getMainLayout());
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    MainViewController controller = loader.getController();
                    controller.setMainApp(mainApp);

                    userField.getScene().getWindow().hide();

                } else {
                    GUIUtil.showAlert(Alert.AlertType.ERROR,
                            null,
                            "Login Failed",
                            "Issue in provided login credentials ",
                            "The username or password does not match.");
                }


            } catch (SQLException e) {
                System.out.println("Sql Exception has occured during login");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception occurred during loading login view");
                e.printStackTrace();
            }
        }

    }

    private boolean isInputValide() {
        String errorMessage = "";

        if (userField.getText() == null || userField.getText().isEmpty()) {
            errorMessage += "No valid username\n";
        }

        if (passwordField.getText() == null || passwordField.getText().isEmpty()) {
            errorMessage += "No valid password\n";

        }
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            GUIUtil.showAlert(Alert.AlertType.ERROR,
                    mainApp.getPrimaryStage(),
                    "Invalid Fields",
                    "Please correct invalid fields",
                    errorMessage);

            return false;
        }
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
