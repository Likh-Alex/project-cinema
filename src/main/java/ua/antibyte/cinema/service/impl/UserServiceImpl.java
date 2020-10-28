package ua.antibyte.cinema.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.dao.UserDao;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.service.UserService;
import ua.antibyte.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        String hashPassword = HashUtil.hashPassword(user.getPassword(), user.getSalt());
        user.setPassword(hashPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}
