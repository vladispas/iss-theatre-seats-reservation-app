package service;

import model.Seat;
import model.Tuple;
import repository.IRepositorySeat;

import java.util.Collection;

public class ServiceSeat {

    private IRepositorySeat repositorySeat;

    public ServiceSeat(IRepositorySeat repositorySeat) {
        this.repositorySeat = repositorySeat;
    }

    public Collection<Seat> findAll() {
        return repositorySeat.findAll();
    }

    public Seat findByID(Tuple ID) {
        return repositorySeat.findByID(ID);
    }

}
