package repository;

import java.util.Collection;

public interface IRepository<T, TID> {

    void add(T elem);

    void delete(T elem);

    void update(T elem, TID id);

    T findByID(TID id);

    Collection<T> findAll();
}
