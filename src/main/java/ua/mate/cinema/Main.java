package ua.mate.cinema;

import javax.naming.AuthenticationException;
import ua.mate.cinema.lib.Injector;
import ua.mate.cinema.model.User;
import ua.mate.cinema.service.security.AuthenticationService;

public class Main {
    private static Injector injector = Injector.getInstance("ua.mate.cinema");
    private static AuthenticationService authService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) throws AuthenticationException {
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("1234");

        authService.register(bob.getEmail(), bob.getPassword());
        System.out.println(authService.login(bob.getEmail(), bob.getPassword()));
    }
}
