package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Book;

import java.util.List;

public class StubbedCSVHandler implements CSVHandler {

    private List<Book> books;

    public StubbedCSVHandler(List<Book> books) {
        this.books = books;
    }

    @Override
    public List<Book> readBooksFromCSV(String fileName) {
        return books;
    }

    @Override
    public void writeBooksToCSV(String fileName, List<Book> books) {
        this.books = books;
    }
}
