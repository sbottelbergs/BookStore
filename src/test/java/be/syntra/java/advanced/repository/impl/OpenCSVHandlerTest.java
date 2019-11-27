package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import static be.syntra.java.advanced.testutil.BookUtil.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class OpenCSVHandlerTest {
    private static final String FILE = "src/test/resources/test.csv";

    private OpenCSVHandler csvHandler;

    @BeforeEach
    public void setUp() {
        csvHandler = new OpenCSVHandler();
    }

    @Test
    public void readBooksFromCSV() throws IOException {
        // given
        givenACSVFileWithTwoBooks();

        // when
        List<Book> books = csvHandler.readBooksFromCSV(FILE);

        // then
        Assertions.assertEquals(Arrays.asList(aHarryPotterBook(), aMichelleObamaBook()), books);
    }

    @Test
    public void writeBooksToCSV() throws IOException {
        // given
        givenAnEmptyCSVFile();
        List<Book> books = Arrays.asList(aHarryPotterBook(), aMichelleObamaBook());

        // when
        csvHandler.writeBooksToCSV(FILE, books);

        // then
        Assertions.assertEquals(books, csvHandler.readBooksFromCSV(FILE));
    }

    @AfterEach
    public void tearDown() {
        File file = new File(FILE);
        file.delete();
    }

    private void givenAnEmptyCSVFile() throws IOException {
        new File(FILE).createNewFile();
    }

    private void givenACSVFileWithTwoBooks() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(FILE));
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("1,J.K. Rowling,Harry Potter And The Philosopher’s Stone,FICTION");
        printWriter.println("2,Michelle Obama,Becoming - A Guided Journal for Discovering Your Voice,NON_FICTION");
        printWriter.close();
    }
}
