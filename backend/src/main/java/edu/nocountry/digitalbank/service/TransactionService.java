package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.transaction.TransactionData;
import edu.nocountry.digitalbank.model.transaction.TransactionResponseSend;

public interface TransactionService {

    TransactionResponseSend sendMoney(String username, TransactionData data);
}