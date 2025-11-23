package com.inventoryfast.controller;

import com.inventoryfast.entity.Product;
import com.inventoryfast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Producto creado");
            response.put("data", createdProduct);
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
    
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", productService.getAllProducts());
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
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", product);
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Producto actualizado");
            response.put("data", updatedProduct);
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Producto eliminado");
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
    
    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam String query) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", productService.searchProducts(query));
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
    
    @GetMapping("/filter/category/{categoryId}")
    public ResponseEntity<?> filterByCategory(@PathVariable String categoryId) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", productService.filterByCategory(categoryId));
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

    @GetMapping("/filter/location/{location}")
    public ResponseEntity<?> filterByLocation(@PathVariable String location) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", productService.filterByLocation(location));
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

    @GetMapping("/filter/lowStock")
    public ResponseEntity<?> filterByLowStock() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", productService.filterByLowStock());
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