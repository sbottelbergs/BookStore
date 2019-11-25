package be.syntra.java.advanced.model;


import be.syntra.java.advanced.repository.SupplierRepository;

import java.util.List;
import java.util.Set;

public abstract class BookRetailer {
    protected SupplierRepository suppliers;

    public BookRetailer(SupplierRepository supplierRepository) {
        this.suppliers = supplierRepository;
    }

    public abstract Set<Supplier> getSuppliers();

    public abstract boolean canOrderBook(int isbn, String supplierName);
    public abstract void orderBook(int isbn, String supplierName);

    public abstract boolean hasBook(int isbn);
    public abstract void sellBook(int isbn);

    public abstract Book findBookByIsbn(int isbn);
    public abstract List<Book> findBooksByType(BookType type);
    public abstract List<Book> findBooksByAuthor(String author);
}
