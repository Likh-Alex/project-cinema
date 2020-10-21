package ua.antibyte.cinema.service.security;

import javax.naming.AuthenticationException;
import ua.antibyte.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
