package ua.mate.cinema.service;

import java.util.List;
import ua.mate.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
