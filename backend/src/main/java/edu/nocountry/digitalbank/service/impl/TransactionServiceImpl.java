package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.account.AccountDetails;
import edu.nocountry.digitalbank.model.transaction.*;
import edu.nocountry.digitalbank.repository.TransactionRepository;
import edu.nocountry.digitalbank.service.AccountService;
import edu.nocountry.digitalbank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionResponseSend sendMoney(String username, TransactionData data) {
        try {
            Account senderAccount = accountService.findByUserUsername(username);
            Account receiverAccount = accountService.findByNumber(data.accountReceiver());

            validateBalance(senderAccount.getBalance(), data.amount());
            accountService.extractBalance(senderAccount, data.amount());
            accountService.addBalance(receiverAccount, data.amount());


            var transaction = Transaction.builder()
                    .senderAccount(senderAccount)
                    .receiverAccount(receiverAccount)
                    .transactionDate(LocalDate.now())
                    .amount(data.amount())
                    .build();

            transactionRepository.save(transaction);

            return new TransactionResponseSend(transaction);

        } catch (Exception e) {
            throw new IntegrityValidation("No se pudo completar la transacción: " + e.getMessage());
        }
    }

    public TransactionListDetails getUserTransactions(String username, boolean limit) {
        try {
            var account = accountService.findByUserUsername(username);
            var accountDetails = new AccountDetails(account);
            List<Transaction> transactions = new ArrayList<>();
            if (limit) {
                transactions = transactionRepository.findTransactionLimitFive(account.getId());
            } else {
                transactions = transactionRepository.findAllTransaction(account.getId());
            }
            List<TransactionDetails> transactionsDetails = new ArrayList<>();
            for (var transaction : transactions) {
                transactionsDetails.add(new TransactionDetails(account, transaction));
            }

            return new TransactionListDetails(accountDetails, transactionsDetails);
        } catch (Exception e) {
            throw new IntegrityValidation(e.getMessage());
        }
    }

    public TransactionListDetails getUserTransactionsDate(String username, LocalDate date) {
        try {
            var account = accountService.findByUserUsername(username);
            var accountDetails = new AccountDetails(account);
            var transactions = transactionRepository.findAllTransactionDate(account.getId(), date);

            List<TransactionDetails> transactionsDetails = new ArrayList<>();
            for (var transaction : transactions) {
                transactionsDetails.add(new TransactionDetails(account, transaction));
            }

            return new TransactionListDetails(accountDetails, transactionsDetails);
        } catch (Exception e) {
            throw new IntegrityValidation(e.getMessage());
        }
    }

    private void validateBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IntegrityValidation("Saldo insuficiente");
        }
    }

    public Transaction getById(Integer id) {
        var transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new IntegrityValidation("La transacción no existe");
        }
        return transaction;
    }
}
