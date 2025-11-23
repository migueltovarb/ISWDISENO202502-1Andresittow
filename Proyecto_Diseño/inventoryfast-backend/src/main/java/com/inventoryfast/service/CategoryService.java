package com.inventoryfast.service;

import com.inventoryfast.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(String id, Category category);
    void deleteCategory(String id);
    Optional<Category> getCategoryById(String id);
    List<Category> getAllCategories();
}