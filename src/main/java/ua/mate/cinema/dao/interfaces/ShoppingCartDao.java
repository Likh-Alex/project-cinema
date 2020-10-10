package ua.mate.cinema.dao.interfaces;

import ua.mate.cinema.model.ShoppingCart;
import ua.mate.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
