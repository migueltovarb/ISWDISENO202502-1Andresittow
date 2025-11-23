package com.inventoryfast.service.impl;

import com.inventoryfast.entity.InventoryMovement;
import com.inventoryfast.entity.Product;
import com.inventoryfast.enums.MovementTypeEnum;
import com.inventoryfast.repository.InventoryMovementRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.InventoryService;
import com.inventoryfast.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);
    
    @Autowired
    private InventoryMovementRepository movementRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AlertService alertService;
    
    @Override
    public InventoryMovement registerEntry(String productId, Integer quantity, String userId) {
        validateInputs(productId, quantity);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        product.setCurrentQuantity(product.getCurrentQuantity() + quantity);
        productRepository.save(product);
        alertService.checkAndGenerateLowStockAlertForProduct(productId);
        
        InventoryMovement movement = new InventoryMovement();
        movement.setProductId(productId);
        movement.setQuantity(quantity);
        movement.setType(MovementTypeEnum.IN);
        movement.setUserId(userId);
        movement.setDate(LocalDate.now().toString());
        movement.setTime(LocalTime.now().toString());
        
        InventoryMovement saved = movementRepository.save(movement);
        log.info("inventory_movement action=IN productId={} quantity={} userId={} date={} time={}",
                productId, quantity, userId, saved.getDate(), saved.getTime());
        return saved;
    }
    
    @Override
    public InventoryMovement registerExit(String productId, Integer quantity, String userId) {
        validateInputs(productId, quantity);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (product.getCurrentQuantity() < quantity) {
            throw new RuntimeException("Stock insuficiente");
        }
        
        product.setCurrentQuantity(product.getCurrentQuantity() - quantity);
        productRepository.save(product);
        alertService.checkAndGenerateLowStockAlertForProduct(productId);
        
        InventoryMovement movement = new InventoryMovement();
        movement.setProductId(productId);
        movement.setQuantity(quantity);
        movement.setType(MovementTypeEnum.OUT);
        movement.setUserId(userId);
        movement.setDate(LocalDate.now().toString());
        movement.setTime(LocalTime.now().toString());
        
        InventoryMovement saved = movementRepository.save(movement);
        log.info("inventory_movement action=OUT productId={} quantity={} userId={} date={} time={}",
                productId, quantity, userId, saved.getDate(), saved.getTime());
        return saved;
    }
    
    @Override
    public InventoryMovement registerAdjustment(String productId, Integer quantity, String userId) {
        validateInputs(productId, quantity);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        product.setCurrentQuantity(product.getCurrentQuantity() + quantity);
        productRepository.save(product);
        alertService.checkAndGenerateLowStockAlertForProduct(productId);
        
        InventoryMovement movement = new InventoryMovement();
        movement.setProductId(productId);
        movement.setQuantity(quantity);
        movement.setType(MovementTypeEnum.ADJUST);
        movement.setUserId(userId);
        movement.setDate(LocalDate.now().toString());
        movement.setTime(LocalTime.now().toString());
        
        InventoryMovement saved = movementRepository.save(movement);
        log.info("inventory_movement action=ADJUST productId={} quantity={} userId={} date={} time={}",
                productId, quantity, userId, saved.getDate(), saved.getTime());
        return saved;
    }
    
    @Override
    public List<InventoryMovement> getMovementHistory(String productId) {
        return movementRepository.findByProductId(productId);
    }
    
    @Override
    public List<InventoryMovement> getAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public List<InventoryMovement> getMovementsByDateRange(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isBlank() || endDate.isBlank()) {
            throw new RuntimeException("start y end son requeridos");
        }
        return movementRepository.findByDateBetween(startDate, endDate);
    }

    private void validateInputs(String productId, Integer quantity) {
        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("productId es requerido");
        }
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("quantity debe ser mayor a 0");
        }
    }
}