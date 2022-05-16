package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Spectator;
import utils.FxUtils;

import java.io.IOException;

public class SignupController {

    private Controller controller;

    @FXML
    private AnchorPane signupPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatField;

    public void setNonGUIController(Controller controller) {
        this.controller = controller;
    }

    public void signupButtonOnAction(ActionEvent e) throws IOException {
        if (!emailField.getText().isBlank() && !passwordField.getText().isBlank() && !repeatField.getText().isBlank()) {
            if (passwordField.getText().equals(repeatField.getText())) {
                if (controller.findSpectatorByEmail(emailField.getText()) == 0) {
                    controller.addSpectator(emailField.getText(), passwordField.getText());

                    Spectator spectator = controller.signin(emailField.getText(), passwordField.getText());

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

                    Stage signupStage = (Stage) signupPane.getScene().getWindow();
                    signupStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Email already in use.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Passwords must match.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Can't leave text fields blank.");
            alert.show();
        }
    }

    public void signinButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signin_view.fxml"));
        Parent root = loader.load();
        SigninContoller signinContoller = loader.getController();
        signinContoller.setNonGUIController(controller);
        Stage stage = (Stage) signupPane.getScene().getWindow();
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
    }
}
