package ua.antibyte.cinema.service;

import java.util.List;
import ua.antibyte.cinema.model.Order;
import ua.antibyte.cinema.model.Ticket;
import ua.antibyte.cinema.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
