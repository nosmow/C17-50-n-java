package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.category.CategoryData;
import edu.nocountry.digitalbank.model.category.CategoryDataUpdate;
import edu.nocountry.digitalbank.model.category.CategoryDetails;

import java.util.List;

public interface CategoryService {
    CategoryDetails saveCategory(String username, CategoryData data);

    Category getById(Integer id);

    CategoryDetails updateCategory(String username, CategoryDataUpdate data);

    List<CategoryDetails> listCategories(String username);
}
