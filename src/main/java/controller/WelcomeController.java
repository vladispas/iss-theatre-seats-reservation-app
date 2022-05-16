package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import model.Spectator;

import java.io.IOException;

public class WelcomeController {

    private Controller controller;
    private Spectator spectator;

    @FXML
    private BorderPane mainPane;

    public void initialize(Controller controller, Spectator spectator) {
        this.controller = controller;
        this.spectator = spectator;
    }

    public void showsButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/shows_view.fxml"));
        mainPane.setCenter(loader.load());

        ShowsController showsController = loader.getController();
        showsController.initialize(controller, spectator);
    }
}
