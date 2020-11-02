package ua.antibyte.cinema.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.antibyte.cinema.dao.UserDao;
import ua.antibyte.cinema.exception.DataProcessingException;
import ua.antibyte.cinema.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User add(User user) {
        return super.add(user, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession().getSession()) {
            Query<User> getUserByLoginQuery
                    = session.createQuery("from User u join fetch u.roles "
                    + "where u.email = :email", User.class);
            getUserByLoginQuery.setParameter("email", email);
            return getUserByLoginQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user by email " + email, e);
        }
    }

    @Override
    public User findById(Long id) {
        return super.findById(id, User.class);
    }
}
