package ua.antibyte.cinema.service;

import java.util.Optional;
import ua.antibyte.cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);
}
