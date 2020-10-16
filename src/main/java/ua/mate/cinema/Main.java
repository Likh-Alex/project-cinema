package ua.mate.cinema;

import java.time.LocalDateTime;
import java.time.Month;
import javax.naming.AuthenticationException;
import org.apache.log4j.Logger;
import ua.mate.cinema.lib.Injector;
import ua.mate.cinema.model.CinemaHall;
import ua.mate.cinema.model.Movie;
import ua.mate.cinema.model.MovieSession;
import ua.mate.cinema.model.User;
import ua.mate.cinema.service.interfaces.CinemaHallService;
import ua.mate.cinema.service.interfaces.MovieService;
import ua.mate.cinema.service.interfaces.MovieSessionService;
import ua.mate.cinema.service.interfaces.OrderService;
import ua.mate.cinema.service.interfaces.ShoppingCartService;
import ua.mate.cinema.service.security.AuthenticationService;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    private static Injector injector = Injector.getInstance("ua.mate.cinema");
    private static AuthenticationService authService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static ShoppingCartService cartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) throws AuthenticationException {
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("1234");
        User authBob = authService.register(bob.getEmail(), bob.getPassword());

        try {
            log.info("User auth successfully" + authService.login(authBob.getEmail(), "1234"));
        } catch (AuthenticationException e) {
            log.warn("Failed to log in", e);
        }
        log.info("user shopping cart empty: " + cartService.getByUser(authBob));

        Movie harryPotterMovie = getMovie("Harry Potter", "Good movie");
        movieService.add(harryPotterMovie);

        Movie ironManMovie = getMovie("Iron Man", "Good movie");
        movieService.add(ironManMovie);

        CinemaHall goldHall = getCinemaHall(20, "VIP");
        cinemaHallService.add(goldHall);

        MovieSession harryPotterSession = getMovieSession(harryPotterMovie,
                goldHall, LocalDateTime.of(2020, Month.OCTOBER, 10, 12, 0, 0));
        movieSessionService.add(harryPotterSession);

        MovieSession ironManSession = getMovieSession(ironManMovie,
                goldHall, LocalDateTime.of(2020, Month.OCTOBER, 11, 12, 0, 0));
        movieSessionService.add(ironManSession);

        shoppingCartService.addSession(harryPotterSession, authBob);
        shoppingCartService.addSession(ironManSession, authBob);
        log.info("user shopping cart result: " + shoppingCartService.getByUser(authBob));

        orderService.completeOrder(shoppingCartService.getByUser(authBob).getTickets(), authBob);
        orderService.getOrderHistory(authBob).forEach(log::info);
    }

    private static Movie getMovie(String title, String description) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        log.info("Movie created successfully");
        return movie;
    }

    private static CinemaHall getCinemaHall(int capacity, String description) {
        CinemaHall hall = new CinemaHall();
        hall.setCapacity(capacity);
        hall.setDescription(description);
        log.info("Cinema hall created successfully");
        return hall;
    }

    private static MovieSession getMovieSession(Movie movie, CinemaHall hall, LocalDateTime time) {
        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(hall);
        session.setShowTime(time);
        log.info("Movie session created successfully");
        return session;
    }
}
