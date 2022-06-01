package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Seat;
import utils.MyListener;
import utils.MySeatListener;

public class SeatController {

    @FXML
    private ImageView seatImage;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(seat);
    }

    private Seat seat;
    private MySeatListener myListener;
    private final Image seatFree = new Image("./assets/seat.png");
    private final Image seatSelected = new Image("./assets/seat-selected.png");
    private final Image seatReserved = new Image("./assets/seat-reserved.png");

    public void setData(Seat seat, MySeatListener myListener) {
        this.seat = seat;
        this.myListener = myListener;
    }

    public void setReserved(Seat seat, MySeatListener myListener) {
        this.seat = seat;
        this.myListener = myListener;
        this.seatImage.setImage(seatReserved);
    }

}
