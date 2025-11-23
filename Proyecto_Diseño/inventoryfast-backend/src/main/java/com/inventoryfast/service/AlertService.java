package com.inventoryfast.service;

import com.inventoryfast.entity.Alert;
import java.util.List;

public interface AlertService {
    Alert createAlert(String productId, String userId, String message, String severity);
    List<Alert> getAlertsByUser(String userId);
    List<Alert> getUnreadAlertsByUser(String userId);
    List<Alert> getAllAlerts();
    void markAlertAsRead(String alertId);
    void deleteAlert(String alertId);
    void checkAndGenerateLowStockAlerts();
    void checkAndGenerateLowStockAlertForProduct(String productId);
}