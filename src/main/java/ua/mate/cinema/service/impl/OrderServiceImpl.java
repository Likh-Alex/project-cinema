package ua.mate.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import ua.mate.cinema.dao.interfaces.OrderDao;
import ua.mate.cinema.lib.Inject;
import ua.mate.cinema.lib.Service;
import ua.mate.cinema.model.Order;
import ua.mate.cinema.model.Ticket;
import ua.mate.cinema.model.User;
import ua.mate.cinema.service.interfaces.OrderService;
import ua.mate.cinema.service.interfaces.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}
