package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.category.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameAndUserId(String categoryName, Integer userId);

    boolean existsByIdAndUserId(Integer categoryId, Integer userId);

    List<CategoryDetails> findByUserUsername(String username);
}
