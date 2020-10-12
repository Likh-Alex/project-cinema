package ua.mate.cinema.service.interfaces;

import ua.mate.cinema.model.MovieSession;
import ua.mate.cinema.model.ShoppingCart;
import ua.mate.cinema.model.User;

public interface ShoppingCartService {
    /**
     * This method is responsible to add a Ticket to the ShoppingCart
     * @param movieSession Contains the information required for a ticket
     * @param user - user who wan't to buy a ticket for a specific MovieSession
     */
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
