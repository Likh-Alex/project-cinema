package ua.antibyte.cinema.model.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private Long movieId;
    private Long cinemaHallId;
    private LocalDateTime showTime;
}
