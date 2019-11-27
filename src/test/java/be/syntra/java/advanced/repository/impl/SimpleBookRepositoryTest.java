package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.model.Book;
import static be.syntra.java.advanced.testutil.BookUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import be.syntra.java.advanced.model.BookType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SimpleBookRepositoryTest {

    private SimpleBookRepository repository;

    @Test
    void addBook() {
        // given
        givenAnEmptyRepository();
        Book book = aFictionBook();

        // when - then
        repository.addBook(book);
    }

    @Test
    void getBookByIsbn() {
        // given
        givenARepositoryWithAFictionAndANonFictionBook();

        // when
        Book book = repository.getBookByIsbn(aFictionBook().getIsbn());

        // then
        assertEquals(aFictionBook(), book);
    }

    @Test
    void removeBook() {
        // given
        givenARepositoryWithAFictionAndANonFictionBook();

        // when
        repository.removeBook(aFictionBook());

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
        givenARepositoryWithAFictionAndANonFictionBook();

        // when
        List<Book> books = repository.getAllBooks();

        // then
        assertEquals(Arrays.asList(aFictionBook(), aNonFictionBook()), books);
    }

    private void givenAnEmptyRepository() {
        repository = new SimpleBookRepository();
    }

    private void givenARepositoryWithAFictionAndANonFictionBook() {
        repository = new SimpleBookRepository();
        repository.addBook(aFictionBook());
        repository.addBook(aNonFictionBook());
    }

    private void givenARepositoryWithAFictionAndTwoNonFictionBooks() {
        repository = new SimpleBookRepository();
        repository.addBook(aFictionBook());
        repository.addBook(aNonFictionBook());
        repository.addBook(anotherNonFictionBook());
    }
}
