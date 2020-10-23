package ua.antibyte.cinema.service.mapper;

import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.MovieSession;
import ua.antibyte.cinema.model.dto.MovieSessionDto;
import ua.antibyte.cinema.service.CinemaHallService;
import ua.antibyte.cinema.service.MovieService;

@Component
public class MovieSessionDtoMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionDtoMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionDto mapMovieSessionToDto(MovieSession movieSession) {
        MovieSessionDto movieSessionDto = new MovieSessionDto();
        movieSessionDto.setId(movieSession.getId());
        movieSessionDto.setMovieId(movieSession.getMovie().getId());
        movieSessionDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionDto.setShowTime(movieSession.getShowTime());
        return movieSessionDto;
    }

    public MovieSession mapDtoToMovieSession(MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.findById(movieSessionDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.findById(movieSessionDto.getCinemaHallId()));
        movieSession.setShowTime(movieSessionDto.getShowTime());
        return movieSession;
    }
}
