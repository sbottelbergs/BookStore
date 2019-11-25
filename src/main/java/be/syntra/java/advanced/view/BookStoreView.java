package be.syntra.java.advanced.view;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.Supplier;

import java.util.List;
import java.util.Set;

public abstract class BookStoreView {
    private BookStoreViewListener viewListener;

    BookStoreViewListener getViewListener() {
        return this.viewListener;
    }

    public void setViewListener(BookStoreViewListener viewListener) {
        this.viewListener = viewListener;
    }

    // Abstract methods

    public abstract void show();

    public abstract void canOrderBook();

    public abstract void orderBook();

    public abstract void displayBooks(List<Book> books);

    public abstract void displayBook(Book book);

    // Feedback

    public abstract void displayMessage(String message);

    public abstract void displayErrorMessage(String message);

    public abstract void displayAvailableSuppliers(Set<Supplier> suppliers);

}
