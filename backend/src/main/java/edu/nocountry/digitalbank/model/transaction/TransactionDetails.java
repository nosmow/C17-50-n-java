package edu.nocountry.digitalbank.model.transaction;

import edu.nocountry.digitalbank.model.account.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetails(
        int transactionId,
        LocalDate transactionDate,
        String accountNumber,
        String username,
        BigDecimal amount
) {
    public TransactionDetails(Account currentAccount, Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getTransactionDate(),
                getAccount(currentAccount.getNumber(), transaction),
                getUsername(currentAccount.getUser().getUsername(), transaction),
                getAmount(currentAccount.getId(), transaction)
        );
    }

    private static String getAccount(String currentAccount, Transaction transaction) {
        if (transaction.getSenderAccount().getNumber().equals(currentAccount)) {
            return transaction.getReceiverAccount().getNumber();
        } else {
            return transaction.getSenderAccount().getNumber();
        }
    }

    private static String getUsername(String currentUsername, Transaction transaction) {
        if (transaction.getSenderAccount().getUser().getUsername().equals(currentUsername)) {
            return transaction.getReceiverAccount().getUser().getUsername();
        } else {
            return transaction.getSenderAccount().getUser().getUsername();
        }
    }

    private static BigDecimal getAmount(int currentAccount, Transaction transaction) {
        BigDecimal original = transaction.getAmount();

        if (transaction.getSenderAccount().getId().equals(currentAccount)) {
            return original.negate();
        } else {
            return original;
        }
    }

}

