package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Spectator;
import utils.FxUtils;

import java.io.IOException;

public class SigninContoller {

    private Controller controller;

    @FXML
    private AnchorPane signinPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    public void setNonGUIController(Controller controller) {
        this.controller = controller;
    }

    public void signinButtonOnAction(ActionEvent e) throws IOException {
        if (!emailField.getText().isBlank() && !passwordField.getText().isBlank()) {
            Spectator spectator = controller.signin(emailField.getText(), passwordField.getText());

            if (spectator != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/welcome_view.fxml"));
                Parent root = loader.load();
                WelcomeController welcomeController = loader.getController();
                welcomeController.initialize(controller, spectator);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.DECORATED);
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                FxUtils.makeWindowDraggable(scene, stage);
                FxUtils.setScenePosition(scene, stage);
                stage.show();

                Stage signinStage = (Stage) signinPane.getScene().getWindow();
                signinStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The email and password do not match.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Can't leave text fields blank.");
            alert.show();
        }
    }

    public void signupButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup_view.fxml"));
        Parent root = loader.load();
        SignupController signupController = loader.getController();
        signupController.setNonGUIController(controller);
        Stage stage = (Stage) signinPane.getScene().getWindow();
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
    }
}
