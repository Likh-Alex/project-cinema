package ua.antibyte.cinema.dao;

import ua.antibyte.cinema.model.ShoppingCart;
import ua.antibyte.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
