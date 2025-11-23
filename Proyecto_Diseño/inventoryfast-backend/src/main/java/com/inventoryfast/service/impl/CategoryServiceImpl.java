package com.inventoryfast.service.impl;

import com.inventoryfast.entity.Category;
import com.inventoryfast.repository.CategoryRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Categoría ya existe");
        }
        return categoryRepository.save(category);
    }
    
    @Override
    public Category updateCategory(String id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }
    
    @Override
    public void deleteCategory(String id) {
        if (!productRepository.findByCategoryId(id).isEmpty()) {
            throw new RuntimeException("No se puede eliminar: la categoría tiene productos asociados");
        }
        categoryRepository.deleteById(id);
    }
    
    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}