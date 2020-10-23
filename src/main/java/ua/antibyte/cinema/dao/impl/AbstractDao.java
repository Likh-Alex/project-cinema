package ua.antibyte.cinema.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.antibyte.cinema.exception.DataProcessingException;

public abstract class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    protected final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T entity, Class<T> clazz) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession().getSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.info(clazz.getSimpleName() + " added successfully");
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add entity "
                    + clazz.getSimpleName() + " " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<T> getAll(Class<T> clazz) {
        try (Session session = sessionFactory.openSession().getSession()) {
            return session.createQuery("from " + clazz.getSimpleName(), clazz)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all " + clazz.getSimpleName(), e);
        }
    }
}
