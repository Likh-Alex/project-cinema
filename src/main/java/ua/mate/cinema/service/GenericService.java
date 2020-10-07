package ua.mate.cinema.service;

import java.util.List;

public interface GenericService<T> {
    T add(T entity);

    List<T> getAll();
}
