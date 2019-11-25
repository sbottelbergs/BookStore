package be.syntra.java.advanced.controller;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookRetailer;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.view.BookStoreView;
import be.syntra.java.advanced.view.BookStoreViewListener;

public class BookStoreController implements BookStoreViewListener {
    private final BookStoreView bookStoreView;
    private final BookRetailer retailer;

    public BookStoreController(BookStoreView bookStoreView, BookRetailer retailer) {
        this.bookStoreView = bookStoreView;
        this.retailer = retailer;

        this.bookStoreView.setViewListener(this);
    }

    public void run() {
        bookStoreView.show();
    }

    @Override
    public void onCanOrderBook(int isbn, String supplierName) {
        if (this.retailer.canOrderBook(isbn, supplierName)) {
            this.bookStoreView.displayMessage("We can order that book for you from: " + supplierName);
        } else {
            this.bookStoreView.displayErrorMessage(supplierName + " doesn't have that book");
        }
    }

    @Override
    public void onOrderBook(int isbn, String supplierName) {
        if (this.retailer.canOrderBook(isbn, supplierName)) {
            this.retailer.orderBook(isbn, supplierName);
            this.bookStoreView.displayMessage(
                    "Your book has been ordered successfully\nIt should be available immediately"
            );
        } else {
            this.bookStoreView.displayErrorMessage(supplierName + " doesn't have that book");
        }
    }

    @Override
    public void onSellBook(int isbn) {
        if (this.retailer.hasBook(isbn)) {
            this.retailer.sellBook(isbn);
            this.bookStoreView.displayMessage("The book has been sold and removed from the inventory");
        } else {
            this.bookStoreView.displayErrorMessage("We don't sell that book");
        }
    }

    @Override
    public void onShowBookByIsbn(int isbn) {
        final Book book = this.retailer.findBookByIsbn(isbn);
        if (book != null) {
            this.bookStoreView.displayBook(book);
        } else {
            this.bookStoreView.displayErrorMessage("We don't have that book");
        }
    }

    @Override
    public void onShowBooksOfType(BookType bookType) {
        this.bookStoreView.displayBooks(this.retailer.findBooksByType(bookType));
    }

    @Override
    public void onShowBooksByAuthor(String author) {
        this.bookStoreView.displayBooks(this.retailer.findBooksByAuthor(author));
    }

    @Override
    public void onShowSuppliers() {
        this.bookStoreView.displayAvailableSuppliers(this.retailer.getSuppliers());
    }
}
