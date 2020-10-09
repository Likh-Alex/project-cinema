package ua.mate.cinema.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.mate.cinema.dao.interfaces.UserDao;
import ua.mate.cinema.exception.DataProcessingException;
import ua.mate.cinema.lib.Dao;
import ua.mate.cinema.model.User;
import ua.mate.cinema.util.HibernateUtil;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession().getSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add user entity "
                    + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession().getSession()) {
            Query<User> getUserByLoginQuery
                    = session.createQuery("from User u where u.email = :email", User.class);
            getUserByLoginQuery.setParameter("email", email);
            return getUserByLoginQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user by email " + email, e);
        }
    }
}
