package ua.antibyte.cinema.service;

import ua.antibyte.cinema.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    User findById(Long id);
}
