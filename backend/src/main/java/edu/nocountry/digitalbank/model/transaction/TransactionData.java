package edu.nocountry.digitalbank.model.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionData(
        @NotBlank
        String accountReceiver,
        @NotNull
        @Positive
        BigDecimal amount
) {
}
