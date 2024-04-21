package edu.nocountry.digitalbank.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDataUpdate(
        @NotNull
        Integer id,
        @NotBlank
        String name
) {
}
