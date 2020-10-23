package ua.antibyte.cinema.service.mapper;

import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.model.dto.MovieDto;

@Component
public class MovieDtoMapper {
    public MovieDto mapMovieToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }

    public Movie mapDtoToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }
}
