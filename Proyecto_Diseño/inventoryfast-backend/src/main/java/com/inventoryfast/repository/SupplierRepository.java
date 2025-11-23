package com.inventoryfast.repository;

import com.inventoryfast.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    Optional<Supplier> findByName(String name);
    Optional<Supplier> findByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}