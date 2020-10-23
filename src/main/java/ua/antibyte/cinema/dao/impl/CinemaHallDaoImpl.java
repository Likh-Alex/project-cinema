package ua.antibyte.cinema.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.antibyte.cinema.dao.CinemaHallDao;
import ua.antibyte.cinema.model.CinemaHall;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall, CinemaHall.class);
    }

    @Override
    public List<CinemaHall> getAll() {
        return super.getAll(CinemaHall.class);
    }

    @Override
    public CinemaHall findById(Long id) {
        return super.findById(id, CinemaHall.class);
    }
}
