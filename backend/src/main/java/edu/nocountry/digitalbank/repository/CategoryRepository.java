package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameAndUserId(String categoryName, Integer userId);

    boolean existsByIdAndUserId(Integer categoryId, Integer userId);
}
