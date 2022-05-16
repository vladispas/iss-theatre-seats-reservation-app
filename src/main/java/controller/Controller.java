package controller;

import model.*;
import service.*;

import java.time.LocalDateTime;
import java.util.Collection;

public class Controller {

    private ServiceSpectator serviceSpectator;
    private ServiceShow serviceShow;

    public Controller(ServiceSpectator serviceSpectator, ServiceShow serviceShow) {
        this.serviceSpectator = serviceSpectator;
        this.serviceShow = serviceShow;
    }

//    spectator methods

    public void addSpectator(String email, String password) {
        serviceSpectator.add(email, password);
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

//    other methods

    public Spectator signin(String email, String password) {
        Integer ID = serviceSpectator.findByDetails(email, password);

        if (ID != 0) {
            return serviceSpectator.findByID(ID);
        }

        return null;
    }
}
