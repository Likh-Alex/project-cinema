package ua.antibyte.cinema;

import java.time.LocalDateTime;
import java.time.Month;
import javax.naming.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.antibyte.cinema.config.AppConfig;
import ua.antibyte.cinema.model.CinemaHall;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.service.CinemaHallService;
import ua.antibyte.cinema.service.MovieService;
import ua.antibyte.cinema.service.MovieSessionService;
import ua.antibyte.cinema.service.OrderService;
import ua.antibyte.cinema.service.ShoppingCartService;
import ua.antibyte.cinema.service.security.AuthenticationService;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    private static final ApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) throws AuthenticationException {
        AuthenticationService authService = context.getBean(AuthenticationService.class);

        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("1234");
        User authBob = authService.register(bob.getEmail(), bob.getPassword());

        try {
            log.info("User auth successfully" + authService.login(authBob.getEmail(), "1234"));
        } catch (AuthenticationException e) {
            log.warn("Failed to log in", e);
        }
        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        log.info("user shopping cart empty: " + cartService.getByUser(authBob));

        MovieService movieService = context.getBean(MovieService.class);
        Movie harryPotterMovie = getMovie("Harry Potter", "Good movie");
        movieService.add(harryPotterMovie);

        Movie ironManMovie = getMovie("Iron Man", "Good movie");
        movieService.add(ironManMovie);

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall goldHall = getCinemaHall(20, "VIP");
        cinemaHallService.add(goldHall);

        MovieSession harryPotterSession = getMovieSession(harryPotterMovie,
                goldHall, LocalDateTime.of(2020, Month.OCTOBER, 10, 12, 0, 0));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(harryPotterSession);

        MovieSession ironManSession = getMovieSession(ironManMovie,
                goldHall, LocalDateTime.of(2020, Month.OCTOBER, 11, 12, 0, 0));
        movieSessionService.add(ironManSession);

        cartService.addSession(harryPotterSession, authBob);
        cartService.addSession(ironManSession, authBob);
        log.info("user shopping cart result: " + cartService.getByUser(authBob));

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(cartService.getByUser(authBob).getTickets(), authBob);
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
