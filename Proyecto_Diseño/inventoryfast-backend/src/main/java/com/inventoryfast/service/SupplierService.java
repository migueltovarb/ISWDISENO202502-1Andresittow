package com.inventoryfast.service;

import com.inventoryfast.entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(String id, Supplier supplier);
    void deleteSupplier(String id);
    Optional<Supplier> getSupplierById(String id);
    List<Supplier> getAllSuppliers();
}