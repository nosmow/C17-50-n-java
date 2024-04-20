package edu.nocountry.digitalbank.model.categoryTransaction;

import jakarta.validation.constraints.NotNull;

public record CategoryTransactionData(
        @NotNull
        Integer categoryId,
        @NotNull
        Integer transactionId
) {

}
