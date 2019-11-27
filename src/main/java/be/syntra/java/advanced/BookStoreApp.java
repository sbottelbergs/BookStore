package be.syntra.java.advanced;

import be.syntra.java.advanced.controller.BookStoreController;
import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookRetailer;
import be.syntra.java.advanced.model.LocalBookStore;
import be.syntra.java.advanced.model.Supplier;
import be.syntra.java.advanced.repository.BookRepository;
import be.syntra.java.advanced.repository.SupplierRepository;
import be.syntra.java.advanced.repository.impl.*;
import be.syntra.java.advanced.view.BookStoreView;
import be.syntra.java.advanced.view.ConsoleBookStoreView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookStoreApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("books");
        SupplierRepository suppliers = new SupplierRepositoryImpl(emf);

        BookRetailer bookStore = new LocalBookStore(getLocalInventory(), suppliers);
        BookStoreView bookStoreView = new ConsoleBookStoreView(new Scanner(System.in));

        BookStoreController bookStoreController = new BookStoreController(bookStoreView, bookStore);
        bookStoreController.run();
        emf.close();
    }

    private static BookRepository getLocalInventory() {
        return new SimpleBookRepository();
    }
}
