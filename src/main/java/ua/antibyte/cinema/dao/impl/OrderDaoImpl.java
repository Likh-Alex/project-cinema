package ua.antibyte.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.antibyte.cinema.dao.OrderDao;
import ua.antibyte.cinema.exception.DataProcessingException;
import ua.antibyte.cinema.model.Order;
import ua.antibyte.cinema.model.User;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = sessionFactory.openSession().getSession()) {
            Query<Order> getAllOrdersQuery = session.createQuery("from Order o "
                    + "left join fetch o.tickets "
                    + "where o.user = :user", Order.class);
            getAllOrdersQuery.setParameter("user", user);
            return getAllOrdersQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get orders by user " + user, e);
        }
    }
}
