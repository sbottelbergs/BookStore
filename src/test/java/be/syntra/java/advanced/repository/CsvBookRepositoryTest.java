package be.syntra.java.advanced.repository;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvBookRepositoryTest {

    private CsvBookRepository repository;

    @Test
    void addBook() {
        // given
        givenAnEmptyRepository();

        // when
        repository.addBook(aFictionBook());

        // then
        assertEquals(aFictionBook(), repository.getBookByIsbn(1));
    }

    @Test
    void getBookByIsbn() {
        // given
        givenARepositoryWithTwoBooks();

        // when
        Book book = repository.getBookByIsbn(1);

        // then
        assertEquals(aFictionBook(), book);
    }

    @Test
    void removeBook() {
        // given
        givenARepositoryWithTwoBooks();
        Book book = aFictionBook();

        // when
        repository.removeBook(book);

        // then
        assertEquals(Collections.singletonList(aNonFictionBook()), repository.getAllBooks());
    }

    @Test
    void getBooksByType() {
        // given
        givenARepositoryWithAFictionAndTwoNonFictionBooks();

        // when
        List<Book> books = repository.getBooksByType(BookType.NON_FICTION);

        // then
        assertEquals(Arrays.asList(aNonFictionBook(), anotherNonFictionBook()), books);
    }

    @Test
    void getAllBooks() {
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

    private Book aFictionBook() {
        return new Book(1, "Fiction Author", "Fiction Title", BookType.FICTION);
    }

    private Book aNonFictionBook() {
        return new Book(2, "Non Fiction Author", "Non Fiction Title", BookType.NON_FICTION);
    }

    private Book anotherNonFictionBook() {
        return new Book(0, "A Different Author", "A Different Title", BookType.NON_FICTION);
    }
}
