package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Spectator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingsController {

    private Controller controller;
    private Spectator spectator;
    private List<Booking> bookings = new ArrayList<>();

    @FXML
    private GridPane gridPane;

    public void initialize(Controller controller, Spectator spectator) {
        this.controller = controller;
        this.spectator = spectator;

        for (Booking booking : controller.findAllBookings()) {
            if (spectator.getBookings().contains(booking.getID())) {
                bookings.add(booking);
            }
        }

        int column = 0, row = 0;

        try {
            for (Booking booking : bookings) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/booking_card_view.fxml"));
                AnchorPane anchorPane = loader.load();

                BookingCardViewController bookingCardViewController = loader.getController();
                bookingCardViewController.setData(controller.findShowByID(booking.getShowID()), booking);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
