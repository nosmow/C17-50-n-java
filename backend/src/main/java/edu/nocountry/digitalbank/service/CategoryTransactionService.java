package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionData;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetails;

public interface CategoryTransactionService {

    CategoryTransactionDetails saveCategoryTransaction(String username, CategoryTransactionData data);
}
