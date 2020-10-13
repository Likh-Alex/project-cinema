package ua.mate.cinema.service.interfaces;

import java.util.List;
import ua.mate.cinema.model.Order;
import ua.mate.cinema.model.Ticket;
import ua.mate.cinema.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
