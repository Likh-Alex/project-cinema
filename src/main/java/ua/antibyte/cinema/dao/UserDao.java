package ua.antibyte.cinema.dao;

import java.util.Optional;
import ua.antibyte.cinema.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
