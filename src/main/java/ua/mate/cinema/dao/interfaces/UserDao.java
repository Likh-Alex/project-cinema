package ua.mate.cinema.dao.interfaces;

import java.util.Optional;
import ua.mate.cinema.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
