package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Show implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private String title;
    private LocalDateTime date;
    private Integer duration;
    private Integer numberOfSeats;

    public Show() {
        this.ID = 0;
        this.title = "";
        this.date = LocalDateTime.now();
        this.duration = 0;
        this.numberOfSeats = 66;
    }

    public Show(String title, LocalDateTime date, Integer duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void increaseNumberOfSeats(Integer number) {
        this.numberOfSeats += number;
    }

    public void decreaseNumberOfSeats(Integer number) {
        this.numberOfSeats -= number;
    }

    @Override
    public String toString() {
        return "Show{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
