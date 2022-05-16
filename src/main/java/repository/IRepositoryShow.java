package repository;

import model.Show;

import java.time.LocalDateTime;

public interface IRepositoryShow extends IRepository<Show, Integer> {

    Integer findByDetails(String title, LocalDateTime date, Integer duration);
}
