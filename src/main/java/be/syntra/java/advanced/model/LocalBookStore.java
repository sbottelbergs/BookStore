package be.syntra.java.advanced.model;

import be.syntra.java.advanced.repository.BookRepository;
import be.syntra.java.advanced.repository.SupplierRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LocalBookStore extends BookRetailer {
    private BookRepository localInventory;

    public LocalBookStore(BookRepository localInventory, SupplierRepository suppliers) {
        super(suppliers);
        this.localInventory = localInventory;
    }

    @Override
    public boolean hasBook(int isbn) {
        return this.localInventory.getBookByIsbn(isbn) != null;
    }

    @Override
    public void sellBook(int isbn) {
        Book book = this.localInventory.getBookByIsbn(isbn);
        this.localInventory.removeBook(book);
    }

    @Override
    public Book findBookByIsbn(int isbn) {
        return localInventory.getBookByIsbn(isbn);
    }

    @Override
    public List<Book> findBooksByType(BookType type) {
        return localInventory.getBooksByType(type);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return this.localInventory.getAllBooks().stream()
                .filter((Book book) -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public boolean canOrderBook(int isbn, String supplierName) {
        final Supplier supplier = suppliers.getSupplier(supplierName);
        return supplier != null && supplier.getBookByIsbn(isbn) != null;
    }

    @Override
    public void orderBook(int isbn, String supplierName) {
        if (canOrderBook(isbn, supplierName)) {
            final Supplier supplier = suppliers.getSupplier(supplierName);
            Book book = supplier.getBookByIsbn(isbn);
            supplier.removeBook(book);
            this.localInventory.addBook(book);
        }
    }

    @Override
    public Set<Supplier> getSuppliers() {
        return suppliers != null ? suppliers.getSuppliers() : Collections.emptySet();
    }
}
