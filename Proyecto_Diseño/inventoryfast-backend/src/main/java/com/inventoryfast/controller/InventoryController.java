package com.inventoryfast.controller;

import com.inventoryfast.entity.InventoryMovement;
import com.inventoryfast.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @PostMapping("/entry")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> registerEntry(@RequestBody Map<String, Object> request, Authentication auth) {
        try {
            String productId = (String) request.get("productId");
            Integer quantity = (Integer) request.get("quantity");
            String userId = auth.getName();
            
            InventoryMovement movement = inventoryService.registerEntry(productId, quantity, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Entrada registrada");
            response.put("data", movement);
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
    
    @PostMapping("/exit")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> registerExit(@RequestBody Map<String, Object> request, Authentication auth) {
        try {
            String productId = (String) request.get("productId");
            Integer quantity = (Integer) request.get("quantity");
            String userId = auth.getName();
            
            InventoryMovement movement = inventoryService.registerExit(productId, quantity, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Salida registrada");
            response.put("data", movement);
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
    
    @PostMapping("/adjustment")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> registerAdjustment(@RequestBody Map<String, Object> request, Authentication auth) {
        try {
            String productId = (String) request.get("productId");
            Integer quantity = (Integer) request.get("quantity");
            String userId = auth.getName();
            
            InventoryMovement movement = inventoryService.registerAdjustment(productId, quantity, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Ajuste registrado");
            response.put("data", movement);
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
    
    @GetMapping("/history/{productId}")
    public ResponseEntity<?> getMovementHistory(@PathVariable String productId) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", inventoryService.getMovementHistory(productId));
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
    
    @GetMapping("/history")
    public ResponseEntity<?> getAllMovements() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", inventoryService.getAllMovements());
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

    @GetMapping("/history/range")
    public ResponseEntity<?> getMovementsByDateRange(@RequestParam String start, @RequestParam String end) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", inventoryService.getMovementsByDateRange(start, end));
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