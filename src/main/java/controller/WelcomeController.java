package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import model.Show;
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

    public void bookButtonOnAction(Show show) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking_view.fxml"));
        mainPane.setCenter(loader.load());

        BookingController bookingController = loader.getController();
        bookingController.initialize(controller, spectator, show);
    }

    public void accountButtonOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/account_view.fxml"));
        mainPane.setCenter(loader.load());

        AccountController accountController = loader.getController();
        accountController.initialize(controller, spectator);
    }

    public void bookingsButtonOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookings_view.fxml"));
        mainPane.setCenter(loader.load());

        BookingsController bookingsController = loader.getController();
        bookingsController.initialize(controller, spectator);
    }

    public void signoutButtonOnAction() {
        System.exit(0);
    }
}
