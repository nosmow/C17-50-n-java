package edu.nocountry.digitalbank.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDataCompany(
        @NotBlank
        String company,

        @NotBlank
        String cuit,

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
