package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleBookRepository implements BookRepository {
    List<Book> books;

    public SimpleBookRepository() {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        return books.stream().filter((Book book) -> book.getIsbn() == isbn).findFirst().orElse(null);
    }

    @Override
    public void removeBook(Book book) {
        this.books.remove(book);
    }

    @Override
    public List<Book> getBooksByType(BookType type) {
        return books.stream().filter((Book book) -> book.getType() == type).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}
