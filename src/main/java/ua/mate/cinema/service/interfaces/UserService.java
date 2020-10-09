package ua.mate.cinema.service.interfaces;

import java.util.Optional;
import ua.mate.cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
