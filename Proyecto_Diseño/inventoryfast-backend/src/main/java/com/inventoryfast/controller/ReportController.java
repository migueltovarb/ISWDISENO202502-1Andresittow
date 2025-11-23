package com.inventoryfast.controller;

import com.inventoryfast.entity.Report;
import com.inventoryfast.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @PostMapping("/stock")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> generateStockReport(Authentication auth) {
        try {
            String userId = auth.getName();
            Report report = reportService.generateStockReport(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Reporte de stock generado");
            response.put("data", report);
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
    
    @PostMapping("/movements")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> generateMovementReport(Authentication auth) {
        try {
            String userId = auth.getName();
            Report report = reportService.generateMovementReport(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Reporte de movimientos generado");
            response.put("data", report);
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
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAllReports() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", reportService.getAllReports());
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
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getReportById(@PathVariable String id) {
        try {
            Report report = reportService.getReportById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", report);
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