package ua.mate.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.mate.cinema.dao.interfaces.CinemaHallDao;
import ua.mate.cinema.exception.DataProcessingException;
import ua.mate.cinema.lib.Dao;
import ua.mate.cinema.model.CinemaHall;
import ua.mate.cinema.util.HibernateUtil;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession().getSession();
            transaction = session.beginTransaction();
            session.persist(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add cinemaHall entity "
                    + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession().getSession()) {
            Query<CinemaHall> getAllCinemaHallsQuery = session
                    .createQuery("from CinemaHall", CinemaHall.class);
            return getAllCinemaHallsQuery.getResultList();
        }
    }
}
