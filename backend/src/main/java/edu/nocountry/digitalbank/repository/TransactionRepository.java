package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "SELECT * FROM transactions WHERE sender_account_id = :id OR receiver_account_id = :id ORDER BY transaction_date DESC LIMIT 5", nativeQuery = true)
    List<Transaction> findTransactionLimitFive(Integer id);

    @Query(value = "SELECT * FROM transactions WHERE sender_account_id = :id OR receiver_account_id = :id ORDER BY transaction_date DESC", nativeQuery = true)
    List<Transaction> findAllTransaction(Integer id);
}
