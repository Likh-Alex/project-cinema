package ua.antibyte.cinema.service;

import java.time.LocalDate;
import java.util.List;
import ua.antibyte.cinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
