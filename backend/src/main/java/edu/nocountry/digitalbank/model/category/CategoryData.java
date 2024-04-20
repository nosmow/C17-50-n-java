package edu.nocountry.digitalbank.model.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryData(
        @NotBlank
        String name
) {
}
