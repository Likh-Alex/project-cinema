package ua.mate.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.mate.cinema.dao.interfaces.TicketDao;
import ua.mate.cinema.exception.DataProcessingException;
import ua.mate.cinema.lib.Dao;
import ua.mate.cinema.model.Ticket;
import ua.mate.cinema.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger log = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession().getSession();
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            log.info("Ticket added successfully");
            return ticket;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
