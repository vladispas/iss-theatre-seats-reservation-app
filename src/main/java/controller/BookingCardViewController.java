package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Booking;
import model.Show;

import java.time.format.DateTimeFormatter;

public class BookingCardViewController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label seatsLabel;

    @FXML
    private Label titleLabel;

    private Show show;
    private Booking booking;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void setData(Show show, Booking booking) {
        this.show = show;
        this.booking = booking;
        titleLabel.setText(titleLabel.getText() + " " + show.getTitle());
        dateLabel.setText(show.getDate().format(DATE_TIME_FORMATTER));
    }

}
