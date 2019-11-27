package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleCSVHandler implements CSVHandler {
    private static final String COMMA_DELIMITER = ",";

    @Override
    public List<Book> readBooksFromCSV(String fileName) {
        final List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                books.add(
                        new Book(
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
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Book book : books) {
                printWriter.println(
                        String.format("%d,%s,%s,%s",
                                book.getIsbn(),
                                book.getAuthor(),
                                book.getTitle(),
                                book.getType())
                );
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
