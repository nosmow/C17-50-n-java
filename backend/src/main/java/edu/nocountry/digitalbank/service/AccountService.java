package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.user.User;

import java.math.BigDecimal;

public interface AccountService {

    void saveAccount(User user);

    Account findByUserUsername(String username);

    Account findByNumber(String number);

    void extractBalance(Account senderAccount, BigDecimal amount);

    void addBalance(Account receiverAccount, BigDecimal amount);
}
