package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.transaction.Transaction;
import edu.nocountry.digitalbank.model.transaction.TransactionData;
import edu.nocountry.digitalbank.model.transaction.TransactionListDetails;
import edu.nocountry.digitalbank.model.transaction.TransactionResponseSend;

public interface TransactionService {

    TransactionResponseSend sendMoney(String username, TransactionData data);

    TransactionListDetails getUserTransactions(String username);

    Transaction getById(Integer id);
}
