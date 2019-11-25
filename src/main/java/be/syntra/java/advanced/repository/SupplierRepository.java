package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Supplier;

import java.util.Set;

public interface SupplierRepository {
    Supplier getSupplier(String name);
    Set<Supplier> getSuppliers();
}
