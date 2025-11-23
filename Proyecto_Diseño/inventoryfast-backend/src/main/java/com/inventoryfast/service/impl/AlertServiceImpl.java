package com.inventoryfast.service.impl;

import com.inventoryfast.entity.Alert;
import com.inventoryfast.entity.Product;
import com.inventoryfast.repository.AlertRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    
    @Autowired
    private AlertRepository alertRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Alert createAlert(String productId, String userId, String message, String severity) {
        Alert alert = new Alert(productId, userId, message, severity);
        return alertRepository.save(alert);
    }
    
    @Override
    public List<Alert> getAlertsByUser(String userId) {
        return alertRepository.findByUserId(userId);
    }
    
    @Override
    public List<Alert> getUnreadAlertsByUser(String userId) {
        return alertRepository.findByUserIdAndIsReadFalse(userId);
    }
    
    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    
    @Override
    public void markAlertAsRead(String alertId) {
        Alert alert = alertRepository.findById(alertId)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        alert.setRead(true);
        alertRepository.save(alert);
    }
    
    @Override
    public void deleteAlert(String alertId) {
        alertRepository.deleteById(alertId);
    }
    
    @Override
    public void checkAndGenerateLowStockAlerts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getCurrentQuantity() < product.getMinQuantity()) {
                String message = "Stock bajo para: " + product.getName();
                // Avoid duplicate unread alerts per product
                boolean existsUnread = !alertRepository.findByProductIdAndIsReadFalse(product.getId()).isEmpty();
                if (!existsUnread) {
                    Alert alert = new Alert(product.getId(), null, message, "HIGH");
                    alertRepository.save(alert);
                }
            }
        }
    }

    @Override
    public void checkAndGenerateLowStockAlertForProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElse(null);
        if (product == null) return;
        if (product.getCurrentQuantity() != null && product.getMinQuantity() != null
                && product.getCurrentQuantity() < product.getMinQuantity()) {
            boolean existsUnread = !alertRepository.findByProductIdAndIsReadFalse(product.getId()).isEmpty();
            if (!existsUnread) {
                String message = "Stock bajo para: " + product.getName();
                Alert alert = new Alert(product.getId(), null, message, "HIGH");
                alertRepository.save(alert);
            }
        }
    }
}