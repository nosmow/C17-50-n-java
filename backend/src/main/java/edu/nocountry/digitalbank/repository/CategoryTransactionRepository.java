package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryTransactionRepository extends JpaRepository<CategoryTransaction, Integer> {

    CategoryTransaction findFirstByTransactionId(Integer id);

    List<CategoryTransaction> findByCategoryName(String name);

    List<CategoryTransaction> findByCategoryId(Integer idCategory);
}
