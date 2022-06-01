package service;

import model.Spectator;
import repository.IRepositorySpectator;

import java.util.Collection;
import java.util.List;

public class ServiceSpectator {

    private IRepositorySpectator repositorySpectator;

    public ServiceSpectator(IRepositorySpectator repositorySpectator) {
        this.repositorySpectator = repositorySpectator;
    }

    public void add(String email, String password) {
        Spectator spectator = new Spectator(email, password);

        repositorySpectator.add(spectator);
    }

    public void delete(Spectator spectator) {
        repositorySpectator.delete(spectator);
    }

    public Collection<Spectator> findAll() {
        return repositorySpectator.findAll();
    }

    public Spectator findByID(Integer ID) {
        return repositorySpectator.findByID(ID);
    }

    public Integer findByDetails(String email, String password) {
        return repositorySpectator.findByDetails(email, password);
    }

    public Integer findByEmail(String email) {
        return repositorySpectator.findByEmail(email);
    }

    public void addBooking(Integer ID, List<Integer> bookings) {
        repositorySpectator.addBooking(ID, bookings);
    }

    public void changePassword(Integer ID, String password) {
        repositorySpectator.changePassword(ID, password);
    }
}
