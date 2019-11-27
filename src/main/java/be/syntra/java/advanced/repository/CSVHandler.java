package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Book;

import java.util.List;

public interface CSVHandler {
    List<Book> readBooksFromCSV(String fileName);

    void writeBooksToCSV(String fileName, List<Book> books);
}
