package ua.mate.cinema.service.interfaces;

import java.util.List;
import ua.mate.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
