package ua.antibyte.cinema.service.mapper;

import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.CinemaHall;
import ua.antibyte.cinema.model.dto.CinemaHallRequestDto;
import ua.antibyte.cinema.model.dto.CinemaHallResponseDto;

@Component
public class CinemaHallMapper {
    public CinemaHall mapRequestDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponseDto mapCinemaHallToResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
