package com.inventoryfast.controller;

import com.inventoryfast.entity.Alert;
import com.inventoryfast.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    
    @Autowired
    private AlertService alertService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    public ResponseEntity<?> getAlerts(Authentication auth) {
        try {
            String userId = auth.getName();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", alertService.getAlertsByUser(userId));
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    public ResponseEntity<?> getAllAlerts() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", alertService.getAllAlerts());
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/unread")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    public ResponseEntity<?> getUnreadAlerts(Authentication auth) {
        try {
            String userId = auth.getName();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", alertService.getUnreadAlertsByUser(userId));
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}/read")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    public ResponseEntity<?> markAlertAsRead(@PathVariable String id) {
        try {
            alertService.markAlertAsRead(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Alerta marcada como leída");
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteAlert(@PathVariable String id) {
        try {
            alertService.deleteAlert(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Alerta eliminada");
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
}