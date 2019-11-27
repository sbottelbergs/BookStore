package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.repository.BookRepository;

import java.util.List;

public class DbBookRepository implements BookRepository {

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Book getBookByIsbn(int isbn) {
        return null;
    }

    @Override
    public void removeBook(Book book) {

    }

    @Override
    public List<Book> getBooksByType(BookType type) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }
}
