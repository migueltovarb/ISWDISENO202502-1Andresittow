package com.inventoryfast.repository;

import com.inventoryfast.entity.InventoryMovement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryMovementRepository extends MongoRepository<InventoryMovement, String> {
    List<InventoryMovement> findByProductId(String productId);
    List<InventoryMovement> findByUserId(String userId);
    List<InventoryMovement> findByDateBetween(String start, String end);
}