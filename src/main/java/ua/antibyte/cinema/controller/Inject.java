package ua.antibyte.cinema.controller;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.CinemaHall;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.service.CinemaHallService;
import ua.antibyte.cinema.service.MovieService;
import ua.antibyte.cinema.service.MovieSessionService;
import ua.antibyte.cinema.service.security.AuthenticationService;

@RestController
@RequestMapping("/inject")
public class Inject {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService authenticationService;

    public Inject(MovieService movieService, CinemaHallService cinemaHallService,
                  MovieSessionService movieSessionService,
                  AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String inject() {
        Movie ironMan = new Movie("Iron Man", "description");
        movieService.add(ironMan);

        CinemaHall whiteRoom = new CinemaHall(100, "white");
        cinemaHallService.add(whiteRoom);

        MovieSession movieSession = createMovieSession(ironMan, whiteRoom, LocalDateTime.now());
        movieSessionService.add(movieSession);

        authenticationService.register("bob@gmail.com", "1234");

        return "inject";
    }

    private MovieSession createMovieSession(Movie movie, CinemaHall cinemaHall,
                                            LocalDateTime showTime) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(showTime);
        return movieSession;
    }
}
