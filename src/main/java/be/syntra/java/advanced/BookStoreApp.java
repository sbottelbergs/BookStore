package be.syntra.java.advanced;

import be.syntra.java.advanced.controller.BookStoreController;
import be.syntra.java.advanced.model.BookRetailer;
import be.syntra.java.advanced.model.LocalBookStore;
import be.syntra.java.advanced.repository.SupplierRepository;
import be.syntra.java.advanced.view.BookStoreView;
import be.syntra.java.advanced.view.ConsoleBookStoreView;

import java.util.Collections;
import java.util.Scanner;

public class BookStoreApp {
    public static void main(String[] args) {
        BookRetailer bookStore = new LocalBookStore(null, null);
        BookStoreView bookStoreView = new ConsoleBookStoreView(new Scanner(System.in));

        BookStoreController bookStoreController = new BookStoreController(bookStoreView, bookStore);
        bookStoreController.run();
    }
}
