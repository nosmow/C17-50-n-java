package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.category.CategoryData;
import edu.nocountry.digitalbank.model.category.CategoryDetails;
import edu.nocountry.digitalbank.repository.CategoryRepository;
import edu.nocountry.digitalbank.service.CategoryService;
import edu.nocountry.digitalbank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryDetails saveCategory(String username, CategoryData data) {

        var user = userService.findUserUsername(username);

        validateCategoryAndUser(data.name(), user.getId());

        var category = Category.builder()
                .name(data.name())
                .user(user)
                .build();

        categoryRepository.save(category);

        return new CategoryDetails(
                category.getId(),
                category.getName(),
                user.getId());
    }

    public Category getById(Integer id) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new IntegrityValidation("La categoria no existe");
        }
        return category;
    }

    private void validateCategoryAndUser(String categoryName, Integer userId) {
        if (categoryRepository.existsByNameAndUserId(categoryName, userId)) {
            throw new IntegrityValidation("La categoría fue creada anteriormente, crea una diferente");
        }
    }
}
