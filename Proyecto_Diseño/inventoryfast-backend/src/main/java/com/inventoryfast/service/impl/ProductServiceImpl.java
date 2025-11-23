package com.inventoryfast.service.impl;

import com.inventoryfast.entity.Product;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU ya existe");
        }
        return productRepository.save(product);
    }
    
    @Override
    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setMinQuantity(product.getMinQuantity());
        existingProduct.setLocation(product.getLocation());
        return productRepository.save(existingProduct);
    }
    
    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    
    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }
    
    @Override
    public List<Product> filterByCategory(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    @Override
    public List<Product> filterByLocation(String location) {
        return productRepository.findByLocation(location);
    }
    
    @Override
    public List<Product> filterByLowStock() {
        return productRepository.findAll().stream()
            .filter(p -> p.getCurrentQuantity() < p.getMinQuantity())
            .collect(Collectors.toList());
    }
}
