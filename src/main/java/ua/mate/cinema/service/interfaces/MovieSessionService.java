package ua.mate.cinema.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import ua.mate.cinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
