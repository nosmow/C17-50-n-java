package edu.nocountry.digitalbank.model.account;

import java.math.BigDecimal;

public record AccountDetails(
        String username,
        String account,
        BigDecimal balance
) {
    public AccountDetails(Account account) {
        this(
                account.getUser().getUsername(),
                account.getNumber(),
                account.getBalance()
        );
    }
}
