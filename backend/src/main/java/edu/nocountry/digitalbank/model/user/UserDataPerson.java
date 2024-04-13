package edu.nocountry.digitalbank.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserDataPerson(
        @NotBlank
        String name,

        @NotBlank
        String lastname,

        @NotBlank
        String dni,

        @NotNull
        LocalDate birthdate,

        @NotBlank
        String username,
        @NotNull
        UserRol role,
        @NotBlank
        @Email
        String email,
        String phone,
        @NotBlank
        String password) {

}
