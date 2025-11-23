package com.inventoryfast.service.impl;

import com.inventoryfast.entity.InventoryMovement;
import com.inventoryfast.entity.Product;
import com.inventoryfast.repository.InventoryMovementRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryMovementRepository movementRepository;

    @Override
    public byte[] exportProductsXlsx() {
        List<Product> products = productRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Productos");
            // Header
            Row header = sheet.createRow(0);
            String[] cols = {"ID","SKU","Nombre","Descripción","Precio","MinQty","QtyActual","Ubicación","CategoryId","SupplierId","Creado"};
            for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);

            int rowIdx = 1;
            for (Product p : products) {
                Row row = sheet.createRow(rowIdx++);
                int c = 0;
                row.createCell(c++).setCellValue(nvl(p.getId()));
                row.createCell(c++).setCellValue(nvl(p.getSku()));
                row.createCell(c++).setCellValue(nvl(p.getName()));
                row.createCell(c++).setCellValue(nvl(p.getDescription()));
                row.createCell(c++).setCellValue(p.getPrice() != null ? p.getPrice().doubleValue() : 0);
                row.createCell(c++).setCellValue(p.getMinQuantity() != null ? p.getMinQuantity() : 0);
                row.createCell(c++).setCellValue(p.getCurrentQuantity() != null ? p.getCurrentQuantity() : 0);
                row.createCell(c++).setCellValue(nvl(p.getLocation()));
                row.createCell(c++).setCellValue(nvl(p.getCategoryId()));
                row.createCell(c++).setCellValue(nvl(p.getSupplierId()));
                row.createCell(c++).setCellValue(p.getCreatedAt() != null ? p.getCreatedAt().toString() : "");
            }

            autosize(sheet, cols.length);
            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error exportando productos a Excel", e);
        }
    }

    @Override
    public byte[] exportMovementsXlsx() {
        List<InventoryMovement> movements = movementRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Movimientos");
            Row header = sheet.createRow(0);
            String[] cols = {"ID","ProductoId","Cantidad","Tipo","UsuarioId","Fecha","Hora","Notas","Creado"};
            for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);

            int rowIdx = 1;
            for (InventoryMovement m : movements) {
                Row row = sheet.createRow(rowIdx++);
                int c = 0;
                row.createCell(c++).setCellValue(nvl(m.getId()));
                row.createCell(c++).setCellValue(nvl(m.getProductId()));
                row.createCell(c++).setCellValue(m.getQuantity() != null ? m.getQuantity() : 0);
                row.createCell(c++).setCellValue(m.getType() != null ? m.getType().name() : "");
                row.createCell(c++).setCellValue(nvl(m.getUserId()));
                row.createCell(c++).setCellValue(nvl(m.getDate()));
                row.createCell(c++).setCellValue(nvl(m.getTime()));
                row.createCell(c++).setCellValue(nvl(m.getNotes()));
                row.createCell(c++).setCellValue(m.getCreatedAt() != null ? m.getCreatedAt().toString() : "");
            }

            autosize(sheet, cols.length);
            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error exportando movimientos a Excel", e);
        }
    }

    @Override
    public int importProductsXlsx(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Archivo Excel vacío");
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int imported = 0;
            // Expect header row at index 0.
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String sku = getString(row.getCell(1));
                String name = getString(row.getCell(2));
                if (sku == null || sku.isBlank() || name == null || name.isBlank()) continue;

                Product p = productRepository.findBySku(sku).orElse(new Product());
                p.setSku(sku);
                p.setName(name);
                p.setDescription(getString(row.getCell(3)));
                String priceStr = getString(row.getCell(4));
                if (priceStr != null && !priceStr.isBlank()) {
                    try { p.setPrice(new BigDecimal(priceStr)); } catch (NumberFormatException ignored) {}
                }
                p.setMinQuantity(getInteger(row.getCell(5)));
                p.setCurrentQuantity(getInteger(row.getCell(6)));
                p.setLocation(getString(row.getCell(7)));
                p.setCategoryId(getString(row.getCell(8)));
                p.setSupplierId(getString(row.getCell(9)));
                productRepository.save(p);
                imported++;
            }
            return imported;
        } catch (IOException e) {
            throw new RuntimeException("Error importando productos desde Excel", e);
        }
    }

    @Override
    public List<Map<String, Object>> previewImportProductsXlsx(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Archivo Excel vacío");
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Map<String, Object>> preview = new ArrayList<>();
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                Map<String, Object> item = new HashMap<>();
                item.put("row", r);
                String sku = getString(row.getCell(1));
                String name = getString(row.getCell(2));
                item.put("sku", sku);
                item.put("name", name);
                item.put("description", getString(row.getCell(3)));
                item.put("price", getString(row.getCell(4)));
                item.put("minQuantity", getInteger(row.getCell(5)));
                item.put("currentQuantity", getInteger(row.getCell(6)));
                item.put("location", getString(row.getCell(7)));
                item.put("categoryId", getString(row.getCell(8)));
                item.put("supplierId", getString(row.getCell(9)));

                List<String> errors = new ArrayList<>();
                if (sku == null || sku.isBlank()) errors.add("SKU requerido");
                if (name == null || name.isBlank()) errors.add("Nombre requerido");
                String priceStr = getString(row.getCell(4));
                if (priceStr != null && !priceStr.isBlank()) {
                    try { new BigDecimal(priceStr); } catch (NumberFormatException ex) { errors.add("Precio inválido"); }
                }
                item.put("errors", errors);
                item.put("valid", errors.isEmpty());
                preview.add(item);
            }
            return preview;
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo Excel para vista previa", e);
        }
    }

    private static void autosize(Sheet sheet, int cols) {
        for (int i = 0; i < cols; i++) sheet.autoSizeColumn(i);
    }

    private static String nvl(String s) { return s == null ? "" : s; }

    private static String getString(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private static Integer getInteger(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) return (int) cell.getNumericCellValue();
        String s = getString(cell);
        try { return s != null ? Integer.parseInt(s) : null; } catch (NumberFormatException e) { return null; }
    }
}