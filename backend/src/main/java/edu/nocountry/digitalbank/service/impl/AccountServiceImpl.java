package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.repository.AccountRepository;
import edu.nocountry.digitalbank.service.AccountService;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private String generateNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i ++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generateUniqueNumber() {
        String number;
        do {
            number = generateNumber();
        } while (accountRepository.existsByNumber(number));

        return number;
    }

    public void saveAccount(User user) {

        var account = Account.builder()
                .user(user)
                .number(generateUniqueNumber())
                .balance(BigDecimal.valueOf(0))
                .build();

        accountRepository.save(account);
    }

    public Account findByUserUsername(String username) {
        if (!accountRepository.existsByUserUsername(username)) {
            throw new IntegrityValidation("El usuario no tiene una cuenta activa");
        }

        return accountRepository.findByUserUsername(username);
    }

    public Account findByNumber(String number) {
        if (!accountRepository.existsByNumber(number)) {
            throw new IntegrityValidation("El número de cuenta no existe");
        }

        return accountRepository.findByNumber(number);
    }

    public void extractBalance(Account senderAccount, BigDecimal amount) {
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        accountRepository.save(senderAccount);
    }

    public void addBalance(Account receiverAccount, BigDecimal amount) {
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        accountRepository.save(receiverAccount);
    }
}
