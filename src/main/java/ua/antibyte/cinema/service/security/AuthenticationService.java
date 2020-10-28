package ua.antibyte.cinema.service.security;

import ua.antibyte.cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
