package ua.mate.cinema.service.impl;

import java.util.Optional;
import ua.mate.cinema.dao.interfaces.UserDao;
import ua.mate.cinema.lib.Inject;
import ua.mate.cinema.lib.Service;
import ua.mate.cinema.model.User;
import ua.mate.cinema.service.interfaces.UserService;
import ua.mate.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

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
}
