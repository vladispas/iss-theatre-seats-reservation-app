package service;

import model.Show;
import repository.IRepositoryShow;

import java.time.LocalDateTime;
import java.util.Collection;

public class ServiceShow {

    private IRepositoryShow repositoryShow;

    public ServiceShow(IRepositoryShow repositoryShow) {
        this.repositoryShow = repositoryShow;
    }

    public Collection<Show> findAll() {
        return repositoryShow.findAll();
    }

    public Show findByID(Integer ID) {
        return repositoryShow.findByID(ID);
    }

    public Integer findByDetails(String title, LocalDateTime date, Integer duration) {
        return repositoryShow.findByDetails(title, date, duration);
    }
}
