package ua.antibyte.cinema.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.antibyte.cinema.dao.TicketDao;
import ua.antibyte.cinema.model.Ticket;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket add(Ticket ticket) {
        return super.add(ticket, Ticket.class);
    }
}
