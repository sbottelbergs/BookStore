package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.repository.StubbedCSVHandler;
import static be.syntra.java.advanced.testutil.BookUtil.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvBookRepositoryTest {

    private CsvBookRepository repository;

    @Test
    public void addBook() {
        // given
        givenAnEmptyRepository();

        // when
        repository.addBook(aFictionBook());

        // then
        assertEquals(aFictionBook(), repository.getBookByIsbn(1));
    }

    @Test
    public void getBookByIsbn() {
        // given
        givenARepositoryWithTwoBooks();

        // when
        Book book = repository.getBookByIsbn(1);

        // then
        assertEquals(aFictionBook(), book);
    }

    @Test
    public void removeBook() {
        // given
        givenARepositoryWithTwoBooks();
        Book book = aFictionBook();

        // when
        repository.removeBook(book);

        // then
        assertEquals(Collections.singletonList(aNonFictionBook()), repository.getAllBooks());
    }

    @Test
    public void getBooksByType() {
        // given
        givenARepositoryWithAFictionAndTwoNonFictionBooks();

        // when
        List<Book> books = repository.getBooksByType(BookType.NON_FICTION);

        // then
        assertEquals(Arrays.asList(aNonFictionBook(), anotherNonFictionBook()), books);
    }

    @Test
    public void getAllBooks() {
        // given
        givenARepositoryWithTwoBooks();

        // when
        List<Book> allBooks = repository.getAllBooks();

        // then
        assertEquals(Arrays.asList(aFictionBook(), aNonFictionBook()), allBooks);
    }

    private void givenAnEmptyRepository() {
        this.repository = new CsvBookRepository("", new StubbedCSVHandler(new ArrayList<>()));
    }

    private void givenARepositoryWithTwoBooks() {
        this.repository = new CsvBookRepository("",
                new StubbedCSVHandler(new ArrayList<>(
                        Arrays.asList(
                                aFictionBook(),
                                aNonFictionBook()
                        )
                ))
        );
    }

    private void givenARepositoryWithAFictionAndTwoNonFictionBooks() {
        this.repository = new CsvBookRepository("",
                new StubbedCSVHandler(new ArrayList<>(
                        Arrays.asList(
                                aFictionBook(),
                                aNonFictionBook(),
                                anotherNonFictionBook()
                        )
                ))
        );
    }
}
