package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.transaction.Transaction;
import edu.nocountry.digitalbank.model.transaction.TransactionData;
import edu.nocountry.digitalbank.model.transaction.TransactionResponseSend;
import edu.nocountry.digitalbank.repository.TransactionRepository;
import edu.nocountry.digitalbank.service.AccountService;
import edu.nocountry.digitalbank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    private void validateBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IntegrityValidation("Saldo insuficiente");
        }
    }
}
