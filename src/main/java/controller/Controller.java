package controller;

import model.*;
import service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {

    private ServiceSpectator serviceSpectator;
    private ServiceShow serviceShow;
    private ServiceSeat serviceSeat;
    private ServiceBooking serviceBooking;

    public Controller(ServiceSpectator serviceSpectator, ServiceShow serviceShow, ServiceSeat serviceSeat, ServiceBooking serviceBooking) {
        this.serviceSpectator = serviceSpectator;
        this.serviceShow = serviceShow;
        this.serviceSeat = serviceSeat;
        this.serviceBooking = serviceBooking;
    }

//    spectator methods

    public void addSpectator(String email, String password) {
        serviceSpectator.add(email, password);
    }

    public void deleteSpectator(Spectator spectator) {
        serviceSpectator.delete(spectator);
    }

    public Collection<Spectator> findAllSpectators() {
        return serviceSpectator.findAll();
    }

    public Spectator findSpectatorByID(Integer ID) {
        return serviceSpectator.findByID(ID);
    }

    public Integer findSpectatorByDetails(String email, String password) {
        return serviceSpectator.findByDetails(email, password);
    }

    public Integer findSpectatorByEmail(String email) {
        return serviceSpectator.findByEmail(email);
    }

    public void addSpectatorBooking(Integer ID, List<Integer> bookings) {
        serviceSpectator.addBooking(ID, bookings);
    }

    public void changePassword(Integer ID, String password) {
        serviceSpectator.changePassword(ID, password);
    }

//    show methods

    public Collection<Show> findAllShows() {
        return serviceShow.findAll();
    }

    public Show findShowByID(Integer ID) {
        return serviceShow.findByID(ID);
    }

    public Integer findShowByDetails(String title, LocalDateTime date, Integer duration) {
        return serviceShow.findByDetails(title, date, duration);
    }

//    seat methods

    public Collection<Seat> findAllSeats() {
        return serviceSeat.findAll();
    }

    public Seat findSeatByID(Tuple ID) {
        return serviceSeat.findByID(ID);
    }

//    booking methods

    public void addBooking(Integer showID, List<Tuple> seats) {
        serviceBooking.add(showID, seats);
    }

    public void deleteBooking(Booking booking) {
        serviceBooking.delete(booking);
    }

    public void updateBooking(Integer showID, List<Tuple> seats, Integer ID) {
        serviceBooking.update(showID, seats, ID);
    }

    public Booking findBookingByID(Integer ID) {
        return serviceBooking.findByID(ID);
    }

    public Collection<Booking> findAllBookings() {
        return serviceBooking.findAll();
    }

    public Integer findBookingByLastID() {
        return serviceBooking.findByLastID();
    }

//    other methods

    public Spectator signin(String email, String password) {
        Integer ID = serviceSpectator.findByDetails(email, password);

        if (ID != 0) {
            return serviceSpectator.findByID(ID);
        }

        return null;
    }

    public Collection<Seat> findAllReservedSeats(Spectator spectator, Show show) {
        List<Seat> reservedSeats = new ArrayList<>();

        for (Booking booking : serviceBooking.findAll()) {
            if (booking.getShowID().equals(show.getID())) {
                for (Tuple seatID : booking.getSeats()) {
                    reservedSeats.add(serviceSeat.findByID(seatID));
                }
            }
        }

        return reservedSeats;
    }
}
