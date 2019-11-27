package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Supplier;
import be.syntra.java.advanced.repository.SupplierRepository;

import javax.persistence.EntityManagerFactory;
import java.util.Set;

public class SupplierRepositoryImpl implements SupplierRepository {

    private Set<Supplier> suppliers;

    public SupplierRepositoryImpl(EntityManagerFactory emf) {
        suppliers = Set.of(
                new Supplier("DB", new DbBookRepository(emf)),
                new Supplier("CSV", new CsvBookRepository(
                        "src/main/resources/books.csv",
                        new SimpleCSVHandler()
                ))
        );
    }

    @Override
    public Supplier getSupplier(String name) {
        return suppliers.stream()
                .filter((Supplier supplier) -> supplier.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
}
