package ua.antibyte.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.dto.CinemaHallRequestDto;
import ua.antibyte.cinema.model.dto.CinemaHallResponseDto;
import ua.antibyte.cinema.service.CinemaHallService;
import ua.antibyte.cinema.service.mapper.CinemaHallMapper;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallMapper cinemaHallMapper;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallMapper cinemaHallMapper,
                                CinemaHallService cinemaHallService) {
        this.cinemaHallMapper = cinemaHallMapper;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper.mapRequestDtoToCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::mapCinemaHallToResponseDto)
                .collect(Collectors.toList());
    }
}
