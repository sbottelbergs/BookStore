package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.repository.CSVHandler;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OpenCSVHandler implements CSVHandler {
    @Override
    public List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                books.add(new Book(
                                Integer.parseInt(values[0]),
                                values[1],
                                values[2],
                                BookType.valueOf(values[3])
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void writeBooksToCSV(String fileName, List<Book> books) {
        List<String[]> bookParts = books.stream()
                .map(
                        (Book book) -> new String[]{
                                String.valueOf(book.getIsbn()),
                                book.getAuthor(),
                                book.getTitle(),
                                String.valueOf(book.getType())
                        }
                )
                .collect(Collectors.toList());
        try {
            csvWriterAll(bookParts, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void csvWriterAll(List<String[]> stringArray, String fileName) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(stringArray);
        writer.close();
    }
}
