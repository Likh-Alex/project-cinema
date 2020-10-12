package ua.mate.cinema.dao.interfaces;

import java.util.List;
import ua.mate.cinema.model.Order;
import ua.mate.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
