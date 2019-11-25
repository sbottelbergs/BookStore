package be.syntra.java.advanced.model;

import be.syntra.java.advanced.repository.BookRepository;

import java.util.List;

public class Supplier implements BookRepository {

    private String name;
    private BookRepository repository;

    public Supplier(String name, BookRepository repository) {
        this.name = name;
        this.repository = repository;
    }

    public String getName() {
        return name;
    }

    @Override
    public void addBook(Book book) {
        this.repository.addBook(book);
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        return this.repository.getBookByIsbn(isbn);
    }

    @Override
    public void removeBook(Book book) {
        this.repository.removeBook(book);
    }

    @Override
    public List<Book> getBooksByType(BookType type) {
        return this.getBooksByType(type);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.repository.getAllBooks();
    }
}
