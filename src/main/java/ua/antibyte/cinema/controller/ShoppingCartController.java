package ua.antibyte.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.model.dto.ShoppingCartResponseDto;
import ua.antibyte.cinema.service.MovieSessionService;
import ua.antibyte.cinema.service.ShoppingCartService;
import ua.antibyte.cinema.service.UserService;
import ua.antibyte.cinema.service.mapper.ShoppingCartMapper;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartMapper shoppingCartMapping,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService) {
        this.shoppingCartMapper = shoppingCartMapping;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        MovieSession movieSession = movieSessionService.findById(movieSessionId);
        User user = userService.findById(userId);
        shoppingCartService.addMovieSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        User user = userService.findById(userId);
        return shoppingCartMapper
                .mapShoppingCartToResponseDto(shoppingCartService.getByUser(user));
    }
}
