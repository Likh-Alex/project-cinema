package ua.mate.cinema.service.impl;

import java.util.List;
import ua.mate.cinema.dao.MovieDao;
import ua.mate.cinema.lib.Inject;
import ua.mate.cinema.lib.Service;
import ua.mate.cinema.model.Movie;
import ua.mate.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
