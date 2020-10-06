package ua.mate.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T entity);

    List<T> getAll();
}
