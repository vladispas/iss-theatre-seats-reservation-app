package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Show;
import model.Spectator;
import utils.MyListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowsController {

    private Controller controller;
    private Spectator spectator;
    private List<Show> shows = new ArrayList<>();
    private MyListener myListener;

    @FXML
    private Button bookButton;

    @FXML
    private Label showDateLabel;

    @FXML
    private Label showDesc;

    @FXML
    private Label showSeatsLabel;

    @FXML
    private Label showTitle;

    @FXML
    private Label showsLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    public void initialize(Controller controller, Spectator spectator) {
        this.controller = controller;
        this.spectator = spectator;

        shows.addAll(controller.findAllShows());

        int column = 0, row = 0;

        if (shows.size() > 0) {
            setChosenShow(shows.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Show show) {
                    setChosenShow(show);
                }
            };
        }

        try {
            for (Show show : shows) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/show_card_view.fxml"));
                AnchorPane anchorPane = loader.load();

                ShowController showController = loader.getController();
                showController.setData(show, myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setChosenShow(Show show) {
        showTitle.setText(show.getTitle());
        String[] date = show.getDate().toString().split("T");
        showDateLabel.setText("Date: " + date[0] + " Time: " + date[1]);
        showSeatsLabel.setText("Remaining seats: " + show.getNumberOfSeats().toString());
    }
}
