package ua.antibyte.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.dao.CinemaHallDao;
import ua.antibyte.cinema.model.CinemaHall;
import ua.antibyte.cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall findById(Long id) {
        return cinemaHallDao.findById(id);
    }
}
