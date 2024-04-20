package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransaction;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionData;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetails;
import edu.nocountry.digitalbank.model.transaction.TransactionDetails;
import edu.nocountry.digitalbank.repository.CategoryTransactionRepository;
import edu.nocountry.digitalbank.service.AccountService;
import edu.nocountry.digitalbank.service.CategoryService;
import edu.nocountry.digitalbank.service.CategoryTransactionService;
import edu.nocountry.digitalbank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryTransactionServiceImpl implements CategoryTransactionService {

    private final CategoryTransactionRepository categoryTransactionRepository;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    public CategoryTransactionDetails saveCategoryTransaction(String username, CategoryTransactionData data) {

        var currentAccount = accountService.findByUserUsername(username);
        var categoryTransactionExists = categoryTransactionRepository.findFirstByTransactionId(data.transactionId());
        var transaction = transactionService.getById(data.transactionId());
        var category = categoryService.getById(data.categoryId());

        var categoryTransaction = CategoryTransaction.builder()
                .transaction(transaction)
                .category(category)
                .build();

        if (categoryTransactionExists != null) {
            categoryTransaction.setId(categoryTransactionExists.getId());
        }

        categoryTransactionRepository.save(categoryTransaction);

        return new CategoryTransactionDetails(category, new TransactionDetails(currentAccount, transaction));
    }
}
