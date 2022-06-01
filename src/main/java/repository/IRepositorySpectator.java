package repository;

import model.Spectator;

import java.util.List;

public interface IRepositorySpectator extends IRepository<Spectator, Integer> {

    Integer findByDetails(String email, String password);

    Integer findByEmail(String email);

    void addBooking(Integer ID, List<Integer> bookings);

    void changePassword(Integer ID, String password);
}
