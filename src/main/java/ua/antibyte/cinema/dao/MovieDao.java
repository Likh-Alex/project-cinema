package ua.antibyte.cinema.dao;

import java.util.List;
import ua.antibyte.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    Movie findById(Long id);

    List<Movie> getAll();
}
