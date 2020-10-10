package ua.mate.cinema.service.impl;

import ua.mate.cinema.dao.interfaces.ShoppingCartDao;
import ua.mate.cinema.dao.interfaces.TicketDao;
import ua.mate.cinema.lib.Inject;
import ua.mate.cinema.lib.Service;
import ua.mate.cinema.model.MovieSession;
import ua.mate.cinema.model.ShoppingCart;
import ua.mate.cinema.model.Ticket;
import ua.mate.cinema.model.User;
import ua.mate.cinema.service.interfaces.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ticketDao.add(ticket);

        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
