package com.inventoryfast.service.impl;

import com.inventoryfast.entity.Supplier;
import com.inventoryfast.repository.SupplierRepository;
import com.inventoryfast.repository.ProductRepository;
import com.inventoryfast.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Supplier createSupplier(Supplier supplier) {
        if (supplierRepository.existsByName(supplier.getName())) {
            throw new RuntimeException("Proveedor ya existe");
        }
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }
        return supplierRepository.save(supplier);
    }
    
    @Override
    public Supplier updateSupplier(String id, Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        existingSupplier.setName(supplier.getName());
        existingSupplier.setContact(supplier.getContact());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setPhone(supplier.getPhone());
        return supplierRepository.save(existingSupplier);
    }
    
    @Override
    public void deleteSupplier(String id) {
        if (!productRepository.findAll().stream()
            .noneMatch(p -> id.equals(p.getSupplierId()))) {
            throw new RuntimeException("No se puede eliminar: el proveedor tiene productos asociados");
        }
        supplierRepository.deleteById(id);
    }
    
    @Override
    public Optional<Supplier> getSupplierById(String id) {
        return supplierRepository.findById(id);
    }
    
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}