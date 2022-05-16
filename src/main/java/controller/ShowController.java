package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Show;
import utils.MyListener;

import java.time.format.DateTimeFormatter;

public class ShowController {

    @FXML
    private ImageView cardImage;

    @FXML
    private Label dateLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(show);
    }

    private Show show;
    private MyListener myListener;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void setData(Show show, MyListener myListener) {
        this.show = show;
        this.myListener = myListener;
        titleLabel.setText(show.getTitle());
        dateLabel.setText(show.getDate().format(DATE_TIME_FORMATTER));
    }
}
