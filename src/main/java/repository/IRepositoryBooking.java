package repository;

import model.Booking;

public interface IRepositoryBooking extends IRepository<Booking, Integer> {

    Integer findByLastID();

}
