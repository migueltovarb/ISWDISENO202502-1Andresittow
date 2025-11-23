package com.inventoryfast.repository;

import com.inventoryfast.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findBySku(String sku);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(String categoryId);
    List<Product> findByLocation(String location);
    boolean existsBySku(String sku);
}