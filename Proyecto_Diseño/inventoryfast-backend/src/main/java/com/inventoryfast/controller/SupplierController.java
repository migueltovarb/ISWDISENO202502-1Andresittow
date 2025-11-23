package com.inventoryfast.controller;

import com.inventoryfast.entity.Supplier;
import com.inventoryfast.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", suppliers);
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
    public ResponseEntity<?> getSupplierById(@PathVariable String id) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", supplierService.getSupplierById(id));
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

    @PostMapping
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier created = supplierService.createSupplier(supplier);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Proveedor creado");
            response.put("data", created);
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

    @PutMapping("/{id}")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSupplier(@PathVariable String id, @RequestBody Supplier supplier) {
        try {
            Supplier updated = supplierService.updateSupplier(id, supplier);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Proveedor actualizado");
            response.put("data", updated);
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
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id) {
        try {
            supplierService.deleteSupplier(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Proveedor eliminado");
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