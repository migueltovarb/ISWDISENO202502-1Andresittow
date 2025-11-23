package com.inventoryfast.service;

import com.inventoryfast.entity.InventoryMovement;
import com.inventoryfast.enums.MovementTypeEnum;
import java.util.List;

public interface InventoryService {
    InventoryMovement registerEntry(String productId, Integer quantity, String userId);
    InventoryMovement registerExit(String productId, Integer quantity, String userId);
    InventoryMovement registerAdjustment(String productId, Integer quantity, String userId);
    List<InventoryMovement> getMovementHistory(String productId);
    List<InventoryMovement> getAllMovements();
    List<InventoryMovement> getMovementsByDateRange(String startDate, String endDate);
}