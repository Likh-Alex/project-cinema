package ua.antibyte.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.dto.UserResponseDto;
import ua.antibyte.cinema.service.UserService;
import ua.antibyte.cinema.service.mapper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        return userMapper.mapUserToResponseDto(userService.findByEmail(email).get());
    }
}
