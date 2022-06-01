package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import model.Spectator;

public class AccountController {

    private Controller controller;
    private Spectator spectator;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField repeatPassword;

    public void initialize(Controller controller, Spectator spectator) {
        this.controller = controller;
        this.spectator = spectator;
    }

    public void updateButtonOnAction() {
        if (!newPassword.getText().isBlank() && !repeatPassword.getText().isBlank()) {
            if (newPassword.getText().equals(repeatPassword.getText())) {
                controller.changePassword(spectator.getID(), newPassword.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Passwords changed.");
                alert.show();
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

    public void deleteButtonOnAction() {
        controller.deleteSpectator(spectator);
        System.exit(0);
    }

}
