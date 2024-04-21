package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionData;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDataDelete;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetails;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetailsList;

public interface CategoryTransactionService {

    CategoryTransactionDetails saveCategoryTransaction(String username, CategoryTransactionData data);

    void deleteCategoryTransaction(CategoryTransactionDataDelete data);

    CategoryTransactionDetailsList listByCategory(String username, String category);
}
