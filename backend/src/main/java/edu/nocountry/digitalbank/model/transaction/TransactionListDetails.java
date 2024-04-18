package edu.nocountry.digitalbank.model.transaction;

import edu.nocountry.digitalbank.model.account.AccountDetails;

import java.util.List;

public record TransactionListDetails(
        AccountDetails accountDetails,
        List<TransactionDetails> transactions
) {
}
