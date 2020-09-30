package ua.mate.cinema.dao;

import java.util.List;
import ua.mate.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
