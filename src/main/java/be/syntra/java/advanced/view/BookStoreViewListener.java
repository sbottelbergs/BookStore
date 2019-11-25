package be.syntra.java.advanced.view;

import be.syntra.java.advanced.model.BookType;

public interface BookStoreViewListener {
    void onCanOrderBook(int isbn, String supplierName);
    void onOrderBook(int isbn, String supplierName);

    void onSellBook(int isbn);

    void onShowBookByIsbn(int isbn);
    void onShowBooksOfType(BookType type);
    void onShowBooksByAuthor(String author);

    void onShowSuppliers();
}
