package com.inventoryfast.controller;

import com.inventoryfast.entity.Category;
import com.inventoryfast.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", categories);
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", categoryService.getCategoryById(id));
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category created = categoryService.createCategory(category);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Categoría creada");
            response.put("data", created);
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody Category category) {
        try {
            Category updated = categoryService.updateCategory(id, category);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Categoría actualizada");
            response.put("data", updated);
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        try {
            categoryService.deleteCategory(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Categoría eliminada");
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
}