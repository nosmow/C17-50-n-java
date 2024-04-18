package edu.nocountry.digitalbank.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseSend(
        LocalDate transactionDate,
        String accountSender,
        String usernameSender,
        BigDecimal balance,
        String accountReceiver,
        String usernameReceiver,
        BigDecimal amount
) {
    public TransactionResponseSend(Transaction transaction) {
        this(
                transaction.getTransactionDate(),
                transaction.getSenderAccount().getNumber(),
                transaction.getSenderAccount().getUser().getUsername(),
                transaction.getSenderAccount().getBalance(),
                transaction.getReceiverAccount().getNumber(),
                transaction.getReceiverAccount().getUser().getUsername(),
                transaction.getAmount()
        );
    }
}
