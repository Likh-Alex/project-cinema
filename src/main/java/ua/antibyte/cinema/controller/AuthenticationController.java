package ua.antibyte.cinema.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.dto.UserRequestDto;
import ua.antibyte.cinema.service.security.AuthenticationService;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public void register(@RequestBody UserRequestDto userDto) {
        authService.register(userDto.getEmail(), userDto.getPassword());
    }
}
