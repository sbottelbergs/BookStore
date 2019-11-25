package be.syntra.java.advanced.view;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.model.Supplier;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleBookStoreView extends BookStoreView {
    private Scanner scanner;

    public ConsoleBookStoreView(Scanner scanner) {
        this.scanner = scanner;
        this.showWelcomeMessage();
    }

    public void show() {
        String userInput;
        do {
            userInput = scanner.nextLine().toLowerCase();
            try {
                switch (userInput) {
                    case "show book":
                        showBooksByIsbn();
                        break;
                    case "show books by type":
                        showBooksByType();
                        break;
                    case "show books by author":
                        showBooksByAuthor();
                        break;
                    case "order book":
                        orderBook();
                        break;
                    case "sell book":
                        sellBook();
                        break;
                    case "exit":
                        System.out.println("Thank you! Come again!");
                        break;
                    default:
                        System.err.println("Unknown command: " + userInput);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Oops. Let's try that again!");
            }
            this.displayOptions();
        } while (!userInput.equalsIgnoreCase("exit"));
    }

    private void displayOptions() {
        System.out.println("=============");
        System.out.println("• show book");
        System.out.println("• show books by type");
        System.out.println("• show books by author");
        System.out.println("• order book");
        System.out.println("• sell book");
        System.out.println("• exit");
        System.out.println("=============");
    }

    private void showBooksByIsbn() {
        System.out.println("Enter the ISBN number of the book you wish to view:");
        final int isbn = Integer.parseInt(scanner.nextLine());
        if (this.getViewListener() != null) {
            this.getViewListener().onShowBookByIsbn(isbn);
        }
    }

    private void showBooksByType() {
        System.out.println("Which type of book are you looking for? (FICTION, NON_FICTION)");
        BookType bookType = BookType.valueOf(scanner.nextLine().toUpperCase());
        if (this.getViewListener() != null) {
            this.getViewListener().onShowBooksOfType(bookType);
        }
    }

    private void showBooksByAuthor() {
        System.out.println("Which author?");
        final String author = scanner.nextLine();
        if (this.getViewListener() != null) {
            this.getViewListener().onShowBooksByAuthor(author);
        }
    }

    private void showSuppliers() {
        if (this.getViewListener() != null) {
            this.getViewListener().onShowSuppliers();
        }
    }

    private void sellBook() {
        System.out.println("Enter the ISBN number of the book you are selling:");
        int isbn = Integer.parseInt(scanner.nextLine());
        if (this.getViewListener() != null) {
            this.getViewListener().onSellBook(isbn);
        }
    }

    private void showWelcomeMessage() {
        System.out.println("BOOKSTORE MANAGER");
        this.displayOptions();
    }

    @Override
    public void canOrderBook() {
        System.out.println();
        showSuppliers();
        System.out.println("Which supplier would you like to use?");
        final String supplierName = scanner.nextLine();
        System.out.println("Enter the book's ISBN number:");
        final int isbn = Integer.parseInt(scanner.nextLine());
        if (this.getViewListener() != null) {
            this.getViewListener().onCanOrderBook(isbn, supplierName);
        }
    }

    @Override
    public void orderBook() {
        System.out.println();
        System.out.println("Book order form");
        System.out.println("===============");
        showSuppliers();
        System.out.println("Which supplier would you like to use?");
        final String supplierName = scanner.nextLine();
        System.out.println("Enter the book's ISBN number:");
        final int isbn = Integer.parseInt(scanner.nextLine());

        if (this.getViewListener() != null) {
            this.getViewListener().onOrderBook(isbn, supplierName);
        }
    }

    @Override
    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.err.println("No books found!");
        } else {
            System.out.println();
            for (Book book : books) {
                displayBook(book);
                System.out.println("=============");
            }
            System.out.println();
        }
    }

    @Override
    public void displayBook(Book book) {
        System.out.println(book);
    }

    @Override
    public void displayAvailableSuppliers(Set<Supplier> suppliers) {
        if (!suppliers.isEmpty()) {
            System.out.println("Available suppliers: ");
            System.out.println("=====================");
            for (Supplier supplier : suppliers) {
                System.out.println(supplier.getName());
            }
        } else {
            System.err.println("No suppliers available");
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayErrorMessage(String message) {
        System.err.println(message);
    }
}
