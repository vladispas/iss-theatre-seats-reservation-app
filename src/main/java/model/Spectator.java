package model;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spectator implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private String email;
    private String password;
    private List<Integer> bookings;

    public Spectator() {
        this.ID = 0;
        this.email = "";
        this.password = "";
        this.bookings = new ArrayList<>();
    }

    public Spectator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getBookings() {
        return bookings;
    }

    public void setBookings(List<Integer> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Integer booking) {
        this.bookings.add(booking);
    }

    public void deleteBooking(Integer booking) {
        this.bookings.remove(booking);
    }

    public void updateBooking(Integer ID, Integer booking) {
        this.bookings.set(ID, booking);
    }

    @Override
    public String toString() {
        return "Spectator{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
