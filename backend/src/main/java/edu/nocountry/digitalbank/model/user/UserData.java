package edu.nocountry.digitalbank.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserData(
        @NotNull
        String username,
        @NotNull
        UserRol role,
        @NotBlank
        @Email
        String email,
        String phone,
        @NotBlank
        String country,
        @NotBlank
        String password
) {
}
