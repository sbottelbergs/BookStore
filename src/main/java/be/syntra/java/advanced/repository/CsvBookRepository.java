package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;

import java.util.List;
import java.util.stream.Collectors;

public class CsvBookRepository implements BookRepository {
    private final String fileName;
    private final CSVHandler csvHandler;

    public CsvBookRepository(String filename, CSVHandler csvHandler) {
        this.fileName = filename;
        this.csvHandler = csvHandler;
    }

    @Override
    public void addBook(Book book) {
        List<Book> books = csvHandler.readBooksFromCSV(fileName);
        books.add(book);
        csvHandler.writeBooksToCSV(fileName, books);
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        List<Book> books = csvHandler.readBooksFromCSV(fileName);
        return books.stream()
                .filter((Book book) -> book.getIsbn() == isbn)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void removeBook(Book book) {
        List<Book> books = csvHandler.readBooksFromCSV(fileName);
        books.remove(book);
        csvHandler.writeBooksToCSV(fileName, books);
    }

    @Override
    public List<Book> getBooksByType(BookType type) {
        return getAllBooks().stream()
                .filter((Book book) -> book.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return csvHandler.readBooksFromCSV(fileName);
    }
}
