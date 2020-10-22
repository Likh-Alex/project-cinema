package ua.antibyte.cinema.dao;

import java.util.List;
import ua.antibyte.cinema.model.Order;
import ua.antibyte.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
