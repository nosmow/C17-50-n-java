package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTransactionRepository extends JpaRepository<CategoryTransaction, Integer> {

    CategoryTransaction findFirstByTransactionId(Integer id);
}
