package repository;

import model.Spectator;

public interface IRepositorySpectator extends IRepository<Spectator, Integer> {

    Integer findByDetails(String email, String password);

    Integer findByEmail(String email);
}
