package ua.antibyte.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.dao.MovieSessionDao;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession findById(Long id) {
        return movieSessionDao.findById(id);
    }
}
