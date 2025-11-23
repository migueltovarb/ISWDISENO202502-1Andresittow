package com.inventoryfast.controller;

import com.inventoryfast.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/export-import")
public class ImportExportController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<byte[]> exportProducts() {
        byte[] data = excelService.exportProductsXlsx();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }

    @GetMapping("/movements")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<byte[]> exportMovements() {
        byte[] data = excelService.exportMovementsXlsx();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=movimientos.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> importProducts(@RequestParam("file") MultipartFile file) {
        int imported = excelService.importProductsXlsx(file);
        return ResponseEntity.ok().body(java.util.Map.of(
                "success", true,
                "message", "Productos importados",
                "imported", imported,
                "timestamp", System.currentTimeMillis()
        ));
    }

    @PostMapping("/products/preview")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> previewImportProducts(@RequestParam("file") MultipartFile file) {
        java.util.List<java.util.Map<String, Object>> preview = excelService.previewImportProductsXlsx(file);
        return ResponseEntity.ok().body(java.util.Map.of(
                "success", true,
                "data", preview,
                "timestamp", System.currentTimeMillis()
        ));
    }
}