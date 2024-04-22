package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.transaction.Transaction;
import edu.nocountry.digitalbank.model.transaction.TransactionData;
import edu.nocountry.digitalbank.model.transaction.TransactionListDetails;
import edu.nocountry.digitalbank.model.transaction.TransactionResponseSend;

import java.time.LocalDate;

public interface TransactionService {

    TransactionResponseSend sendMoney(String username, TransactionData data);

    TransactionListDetails getUserTransactions(String username, boolean limit);

    Transaction getById(Integer id);

    TransactionListDetails getUserTransactionsDate(String username, LocalDate date);
}
