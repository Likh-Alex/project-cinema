package ua.antibyte.cinema.service.mapper;

import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.model.dto.response.UserResponseDto;

@Component
public class UserMapper {
    public UserResponseDto mapUserToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
