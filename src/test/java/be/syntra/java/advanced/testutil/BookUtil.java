package be.syntra.java.advanced.testutil;

import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;

public class BookUtil {
    public static Book aHarryPotterBook() {
        return new Book(1,
                "J.K. Rowling",
                "Harry Potter And The Philosopher’s Stone",
                BookType.FICTION
        );
    }

    public static Book aMichelleObamaBook() {
        return new Book(2,
                "Michelle Obama",
                "Becoming - A Guided Journal for Discovering Your Voice",
                BookType.NON_FICTION
        );
    }

    public static Book aFictionBook() {
        return new Book(1, "Fiction Author", "Fiction Title", BookType.FICTION);
    }

    public static Book aNonFictionBook() {
        return new Book(2, "Non Fiction Author", "Non Fiction Title", BookType.NON_FICTION);
    }

    public static Book anotherNonFictionBook() {
        return new Book(0, "A Different Author", "A Different Title", BookType.NON_FICTION);
    }
}
