package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.*;
import utils.MySeatListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingController {

    private Controller controller;
    private Spectator spectator;
    private Show show;
    private List<Seat> seats = new ArrayList<>();
    private MySeatListener mySeatListener;
    private List<Seat> selectedSeats = new ArrayList<>();
    private List<Seat> reservedSeats = new ArrayList<>();

    @FXML
    private Label showTitle;

    @FXML
    private Label showDateLabel;

    @FXML
    private Label selectedSeatsLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private GridPane gridPane;

    public void initialize(Controller controller, Spectator spectator, Show show) {
        this.controller = controller;
        this.spectator = spectator;
        this.show = show;
        this.reservedSeats.addAll(controller.findAllReservedSeats(spectator, show));
        setShow();

        seats.addAll(controller.findAllSeats());

        int column = 0, row = 0;

        if (seats.size() > 0) {
            mySeatListener = new MySeatListener() {
                @Override
                public void onClickListener(Seat seat) {
                    changeSeatsAndPrice(seat);
                }
            };
        }

        try {
            for (Seat seat : seats) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/seat_button.fxml"));
                AnchorPane anchorPane = loader.load();

                SeatController seatController = loader.getController();

                for (Seat s : reservedSeats) {
                    if (s.getID().equals(seat.getID())) {
                        seatController.setReserved(seat, mySeatListener);
                    } else {
                        seatController.setData(seat, mySeatListener);
                    }
                }

                if (column == 10) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                gridPane.setHgap(0);
                gridPane.setVgap(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setShow() {
        showTitle.setText(show.getTitle());
        String[] date = show.getDate().toString().split("T");
        showDateLabel.setText("Date: " + date[0] + " Time: " + date[1]);
    }

    private void changeSeatsAndPrice(Seat seat) {
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
        } else {
            selectedSeats.add(seat);
        }
        selectedSeatsLabel.setText("Selected seats:");
        Double sum = 0.0;
        for (Seat s : selectedSeats) {
            selectedSeatsLabel.setText(selectedSeatsLabel.getText() + "\nRandul " + s.getID().getPosX() + " Coloana " + s.getID().getPosY());
            sum += s.getPrice();
        }
        priceLabel.setText("Price: " + sum);
    }

    public void bookButtonOnAction(ActionEvent e) throws IOException {
        List<Tuple> seats = new ArrayList<>();
        for (Seat seat : selectedSeats) {
            seats.add(seat.getID());
        }
        controller.addBooking(show.getID(), seats);
        spectator.addBooking(controller.findBookingByLastID());
        controller.addSpectatorBooking(spectator.getID(), spectator.getBookings());

        selectedSeatsLabel.setText("Selected seats:");
        priceLabel.setText("Price:");
    }

}
