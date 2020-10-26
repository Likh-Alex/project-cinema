package ua.antibyte.cinema.service.security;

import javax.naming.AuthenticationException;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.service.ShoppingCartService;
import ua.antibyte.cinema.service.UserService;
import ua.antibyte.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password)
            throws AuthenticationException {
        User user = userService.findByEmail(email);
        String hashPassword = HashUtil.hashPassword(password, user.getSalt());
        if (user.getPassword().equals(hashPassword)) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        shoppingCartService.registerNewShoppingCart(userService.add(user));
        return user;
    }
}
