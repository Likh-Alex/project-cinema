package ua.antibyte.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.dao.MovieDao;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
