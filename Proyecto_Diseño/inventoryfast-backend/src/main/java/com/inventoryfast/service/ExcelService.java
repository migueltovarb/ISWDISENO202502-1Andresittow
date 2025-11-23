package com.inventoryfast.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface ExcelService {
    byte[] exportProductsXlsx();
    byte[] exportMovementsXlsx();
    int importProductsXlsx(MultipartFile file);
    List<Map<String, Object>> previewImportProductsXlsx(MultipartFile file);
}