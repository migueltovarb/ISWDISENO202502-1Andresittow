package com.inventoryfast.service.impl;

import com.inventoryfast.entity.Report;
import com.inventoryfast.entity.Product;
import com.inventoryfast.entity.InventoryMovement;
import com.inventoryfast.repository.ReportRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.repository.InventoryMovementRepository;
import com.inventoryfast.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InventoryMovementRepository movementRepository;
    
    @Override
    public Report generateStockReport(String generatedBy) {
        List<Product> products = productRepository.findAll();
        Map<String, Object> data = new HashMap<>();
        data.put("totalProducts", products.size());
        data.put("products", products);
        data.put("timestamp", System.currentTimeMillis());
        
        Report report = new Report("STOCK_REPORT", data, generatedBy);
        return reportRepository.save(report);
    }
    
    @Override
    public Report generateMovementReport(String generatedBy) {
        List<InventoryMovement> movements = movementRepository.findAll();
        Map<String, Object> data = new HashMap<>();
        data.put("totalMovements", movements.size());
        data.put("movements", movements);
        data.put("timestamp", System.currentTimeMillis());
        
        Report report = new Report("MOVEMENT_REPORT", data, generatedBy);
        return reportRepository.save(report);
    }
    
    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    
    @Override
    public Optional<Report> getReportById(String id) {
        return reportRepository.findById(id);
    }
}