package ua.mate.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.mate.cinema.dao.interfaces.OrderDao;
import ua.mate.cinema.exception.DataProcessingException;
import ua.mate.cinema.lib.Dao;
import ua.mate.cinema.model.Order;
import ua.mate.cinema.model.User;
import ua.mate.cinema.util.HibernateUtil;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession().getSession();
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add order " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession().getSession()) {
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
