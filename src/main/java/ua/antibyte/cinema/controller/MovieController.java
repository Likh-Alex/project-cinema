package ua.antibyte.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.dto.MovieRequestDto;
import ua.antibyte.cinema.model.dto.MovieResponseDto;
import ua.antibyte.cinema.service.MovieService;
import ua.antibyte.cinema.service.mapper.MovieDtoMapper;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieDtoMapper movieDtoMapper;

    public MovieController(MovieService movieService, MovieDtoMapper movieDtoMapper) {
        this.movieService = movieService;
        this.movieDtoMapper = movieDtoMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieDtoMapper.mapRequestDtoToMovie(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(movieDtoMapper::mapMovieToResponseDto)
                .collect(Collectors.toList());
    }
}
