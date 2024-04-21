package edu.nocountry.digitalbank.model.categoryTransaction;

import edu.nocountry.digitalbank.model.transaction.TransactionDetails;

import java.util.List;

public record CategoryTransactionDetailsList(
        String category,
        List<TransactionDetails> transactions
) {
}
