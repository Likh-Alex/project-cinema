package ua.antibyte.cinema.service;

import java.util.List;
import ua.antibyte.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
