package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.category.CategoryData;
import edu.nocountry.digitalbank.model.category.CategoryDataUpdate;
import edu.nocountry.digitalbank.model.category.CategoryDetails;
import edu.nocountry.digitalbank.repository.CategoryRepository;
import edu.nocountry.digitalbank.service.CategoryService;
import edu.nocountry.digitalbank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryDetails saveCategory(String username, CategoryData data) {

        var user = userService.findUserUsername(username);

        validateExistsCategoryAndUser(data.name(), user.getId());

        var category = Category.builder()
                .name(data.name())
                .user(user)
                .build();

        categoryRepository.save(category);

        return new CategoryDetails(
                category.getId(),
                category.getName());
    }

    public Category getById(Integer id) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new IntegrityValidation("La categoria no existe");
        }
        return category;
    }

    public CategoryDetails updateCategory(String username, CategoryDataUpdate data) {

        var user = userService.findUserUsername(username);

        validateCategoryAndUser(data.id(), user.getId());

        var category = categoryRepository.getReferenceById(data.id());
        category.setName(data.name());

        categoryRepository.save(category);

        return new CategoryDetails(
                category.getId(),
                category.getName());
    }

    public List<CategoryDetails> listCategories(String username) {

        return categoryRepository.findByUserUsername(username);
    }

    private void validateExistsCategoryAndUser(String categoryName, Integer userId) {
        if (categoryRepository.existsByNameAndUserId(categoryName, userId)) {
            throw new IntegrityValidation("La categoría fue creada anteriormente, crea una diferente");
        }
    }

    private void validateCategoryAndUser(Integer categoryId, Integer userId) {
        if (!categoryRepository.existsByIdAndUserId(categoryId, userId)) {
            throw new IntegrityValidation("El usuario no ha creado esta categoría anteriormente");
        }
    }
}
