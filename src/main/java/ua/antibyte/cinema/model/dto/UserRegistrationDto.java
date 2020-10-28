package ua.antibyte.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ua.antibyte.cinema.annotation.EmailValidation;
import ua.antibyte.cinema.annotation.FieldsValueMatch;

@Data


@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationDto {
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    private String repeatPassword;
}
