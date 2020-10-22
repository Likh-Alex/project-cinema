package ua.antibyte.cinema.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.antibyte.cinema.dao.MovieDao;
import ua.antibyte.cinema.model.Movie;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie, Movie.class);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
