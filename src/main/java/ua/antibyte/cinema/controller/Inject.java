package ua.antibyte.cinema.controller;

import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.CinemaHall;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.model.Role;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.service.CinemaHallService;
import ua.antibyte.cinema.service.MovieService;
import ua.antibyte.cinema.service.MovieSessionService;
import ua.antibyte.cinema.service.RoleService;
import ua.antibyte.cinema.service.ShoppingCartService;
import ua.antibyte.cinema.service.UserService;

@RestController
@RequestMapping("/inject")
public class Inject {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public Inject(MovieService movieService,
                  CinemaHallService cinemaHallService,
                  MovieSessionService movieSessionService,
                  RoleService roleService,
                  UserService userService,
                  ShoppingCartService shoppingCartService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void init() {
        Role adminRole = Role.of("ADMIN");
        Role userRole = Role.of("USER");
        roleService.add(adminRole);
        roleService.add(userRole);

        User user = new User();
        user.setEmail("bob@gmail.com");
        user.setPassword("1234");
        user.setRoles(Set.of(roleService.getRoleByName("USER"),
                roleService.getRoleByName("ADMIN")));
        shoppingCartService.registerNewShoppingCart(userService.add(user));
    }

    @GetMapping
    public String inject() {
        Movie ironMan = new Movie("Iron Man", "description");
        movieService.add(ironMan);

        CinemaHall whiteRoom = new CinemaHall(100, "white");
        cinemaHallService.add(whiteRoom);

        MovieSession movieSession = createMovieSession(ironMan, whiteRoom, LocalDateTime.now());
        movieSessionService.add(movieSession);

        return "Data was successfully injected";
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
