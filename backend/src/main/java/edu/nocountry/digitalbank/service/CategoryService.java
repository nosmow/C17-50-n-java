package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.category.CategoryData;
import edu.nocountry.digitalbank.model.category.CategoryDetails;

public interface CategoryService {
    CategoryDetails saveCategory(String username, CategoryData data);
}
