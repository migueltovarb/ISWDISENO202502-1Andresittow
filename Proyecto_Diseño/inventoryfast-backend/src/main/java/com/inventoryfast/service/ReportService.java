package com.inventoryfast.service;

import com.inventoryfast.entity.Report;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report generateStockReport(String generatedBy);
    Report generateMovementReport(String generatedBy);
    List<Report> getAllReports();
    Optional<Report> getReportById(String id);
}