package ua.antibyte.cinema.service.mapper;

import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.Movie;
import ua.antibyte.cinema.model.dto.MovieRequestDto;
import ua.antibyte.cinema.model.dto.MovieResponseDto;

@Component
public class MovieDtoMapper {
    public MovieResponseDto mapMovieToResponseDto(Movie movie) {
        MovieResponseDto movieDto = new MovieResponseDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }

    public Movie mapRequestDtoToMovie(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }
}
