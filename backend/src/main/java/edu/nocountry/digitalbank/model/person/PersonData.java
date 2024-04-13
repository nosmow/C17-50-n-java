package edu.nocountry.digitalbank.model.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonData(
        @NotBlank
        String name,

        @NotNull
        Integer userId,

        @NotBlank
        String lastname,

        @NotBlank
        String dni,

        @NotNull
        LocalDate birthdate
) {
}
