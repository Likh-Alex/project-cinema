package ua.antibyte.cinema.dao;

import java.util.List;
import ua.antibyte.cinema.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall findById(Long id);
}
