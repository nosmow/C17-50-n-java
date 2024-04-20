package edu.nocountry.digitalbank.model.categoryTransaction;

import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.transaction.TransactionDetails;

public record CategoryTransactionDetails(
        String category,
        TransactionDetails transaction
) {

    public CategoryTransactionDetails(Category category, TransactionDetails transactionDetails) {
        this(
                category.getName(),
                transactionDetails
        );
    }
}
