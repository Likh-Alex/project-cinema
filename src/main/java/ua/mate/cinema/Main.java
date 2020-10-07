package ua.mate.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import ua.mate.cinema.lib.Injector;
import ua.mate.cinema.model.CinemaHall;
import ua.mate.cinema.model.Movie;
import ua.mate.cinema.model.MovieSession;
import ua.mate.cinema.service.interfaces.CinemaHallService;
import ua.mate.cinema.service.interfaces.MovieService;
import ua.mate.cinema.service.interfaces.MovieSessionService;

public class Main {
    private static Injector injector = Injector.getInstance("ua.mate.cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService hallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {
        Movie ironManMovie = createMovie("Iron Man", "Good movie!");
        movieService.add(ironManMovie);

        Movie starWarsMovie = createMovie("Star Wars", "Not bad movie.");
        movieService.add(starWarsMovie);

        Movie friends = createMovie("Friends", "Not bad movie.");
        movieService.add(friends);

        CinemaHall greenHall = createCinemaHall(100, "Common hall");
        hallService.add(greenHall);

        CinemaHall goldHall = createCinemaHall(20, "VIP hall");
        hallService.add(goldHall);

        MovieSession firstSession07
                = createMovieSession(ironManMovie, greenHall,
                LocalDateTime.of(2020, Month.OCTOBER, 7, 12, 0, 0));
        MovieSession secondSession07
                = createMovieSession(starWarsMovie, goldHall,
                LocalDateTime.of(2020, Month.OCTOBER, 7, 16, 0, 0));
        MovieSession thirdSession07 = createMovieSession(friends, greenHall,
                LocalDateTime.of(2020, Month.OCTOBER, 7, 20, 0, 0));
        MovieSession firstSession08
                = createMovieSession(ironManMovie, goldHall,
                LocalDateTime.of(2020, Month.OCTOBER, 8, 12, 0, 0));
        MovieSession secondSession08
                = createMovieSession(ironManMovie, goldHall,
                LocalDateTime.of(2020, Month.OCTOBER, 7, 16, 0, 0));
        MovieSession thirdSession08
                = createMovieSession(starWarsMovie, greenHall,
                LocalDateTime.of(2020, Month.OCTOBER, 7, 21, 0, 0));

        sessionService.add(firstSession07);
        sessionService.add(secondSession07);
        sessionService.add(thirdSession07);
        sessionService.add(firstSession08);
        sessionService.add(secondSession08);
        sessionService.add(thirdSession08);

        List<MovieSession> availableSessions
                = sessionService.findAvailableSessions(ironManMovie.getId(),
                LocalDate.of(2020, Month.OCTOBER, 7));
        availableSessions.forEach(System.out::println);
    }

    private static Movie createMovie(String title, String description) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        return movie;
    }

    private static CinemaHall createCinemaHall(int capacity, String description) {
        CinemaHall hall = new CinemaHall();
        hall.setCapacity(capacity);
        hall.setDescription(description);
        return hall;
    }

    private static MovieSession createMovieSession(
            Movie movie, CinemaHall hall, LocalDateTime date) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(hall);
        movieSession.setShowTime(date);
        return movieSession;
    }
}
