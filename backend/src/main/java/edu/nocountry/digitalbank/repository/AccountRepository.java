package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByUserUsername(String username);

    boolean existsByNumber(String number);

    Account findByNumber(String number);

    boolean existsByUserUsername(String username);
}
