package com.inventoryfast.service;

import com.inventoryfast.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
    Optional<Product> getProductById(String id);
    List<Product> getAllProducts();
    List<Product> searchProducts(String query);
    List<Product> filterByCategory(String categoryId);
    List<Product> filterByLocation(String location);
    List<Product> filterByLowStock();
}