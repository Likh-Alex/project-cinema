package ua.antibyte.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import ua.antibyte.cinema.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession findById(Long id);
}
