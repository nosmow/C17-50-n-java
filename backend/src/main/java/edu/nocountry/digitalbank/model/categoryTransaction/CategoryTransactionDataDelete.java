package edu.nocountry.digitalbank.model.categoryTransaction;

import jakarta.validation.constraints.NotNull;

public record CategoryTransactionDataDelete(
        @NotNull
        Integer categoryTransactionId
) {

}
