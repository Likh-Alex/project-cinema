package ua.antibyte.cinema.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.dto.MovieSessionRequestDto;
import ua.antibyte.cinema.model.dto.MovieSessionResponseDto;
import ua.antibyte.cinema.service.MovieSessionService;
import ua.antibyte.cinema.service.mapper.MovieSessionDtoMapper;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionDtoMapper movieSessionDtoMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionDtoMapper movieSessionDtoMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionDtoMapper = movieSessionDtoMapper;
    }

    @PostMapping
    public String add(@RequestBody MovieSessionRequestDto movieSessionDto) {
        movieSessionService.add(movieSessionDtoMapper.mapRequestDtoToMovieSession(movieSessionDto));
        return "successful";
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessions(
            @RequestParam Long movieId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionDtoMapper::mapMovieSessionToResponseDto)
                .collect(Collectors.toList());
    }
}
