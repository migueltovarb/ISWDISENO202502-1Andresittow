package com.inventoryfast.repository;

import com.inventoryfast.entity.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {
    List<Alert> findByUserId(String userId);
    List<Alert> findByProductId(String productId);
    List<Alert> findByUserIdAndIsReadFalse(String userId);
    List<Alert> findByProductIdAndIsReadFalse(String productId);
}