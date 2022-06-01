package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private Integer showID;
    private List<Tuple> seats;

    public Booking() {
        this.ID = 0;
        this.showID = 0;
        this.seats = new ArrayList<>();
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getShowID() {
        return showID;
    }

    public void setShowID(Integer showID) {
        this.showID = showID;
    }

    public List<Tuple> getSeats() {
        return seats;
    }

    public void setSeats(List<Tuple> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "ID=" + ID +
                '}';
    }
}
