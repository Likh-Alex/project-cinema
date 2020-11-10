package ua.antibyte.cinema.service.security;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            org.springframework.security.core.userdetails.User.UserBuilder userBuilder
                    = org.springframework.security.core.userdetails
                    .User.withUsername(user.getEmail());
            userBuilder.password(user.getPassword());
            String[] roles = user.getRoles().stream()
                    .map(role -> role.getRoleName().toString())
                    .toArray(String[]::new);
            userBuilder.roles(roles);
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException("User with " + email + "email, not found");
        }
    }
}
