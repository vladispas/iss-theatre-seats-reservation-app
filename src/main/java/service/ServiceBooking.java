package service;

import model.Booking;
import model.Tuple;
import repository.IRepositoryBooking;

import java.util.Collection;
import java.util.List;

public class ServiceBooking {

    private IRepositoryBooking repositoryBooking;

    public ServiceBooking(IRepositoryBooking repositoryBooking) {
        this.repositoryBooking = repositoryBooking;
    }

    public void add(Integer showID, List<Tuple> seats) {
        Booking booking = new Booking();
        booking.setShowID(showID);
        booking.setSeats(seats);

        repositoryBooking.add(booking);
    }

    public void delete(Booking booking) {
        repositoryBooking.delete(booking);
    }

    public void update(Integer showID, List<Tuple> seats, Integer ID) {
        Booking booking = new Booking();
        booking.setShowID(showID);
        booking.setSeats(seats);

        repositoryBooking.update(booking, ID);
    }

    public Booking findByID(Integer ID) {
        return repositoryBooking.findByID(ID);
    }

    public Collection<Booking> findAll() {
        return repositoryBooking.findAll();
    }

    public Integer findByLastID() {
        return repositoryBooking.findByLastID();
    }

}
