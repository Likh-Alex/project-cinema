package ua.mate.cinema.service.security;

import javax.naming.AuthenticationException;
import ua.mate.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
