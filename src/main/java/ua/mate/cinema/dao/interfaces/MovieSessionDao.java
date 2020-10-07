package ua.mate.cinema.dao.interfaces;

import java.time.LocalDate;
import java.util.List;
import ua.mate.cinema.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
