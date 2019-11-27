package be.syntra.java.advanced.entity;

import be.syntra.java.advanced.model.Book;

public class BookMapper {
    public Book map(BookEntity bookEntity) {
        return new Book(
                bookEntity.getIsbn(),
                bookEntity.getAuthor(),
                bookEntity.getTitle(),
                bookEntity.getType()
        );
    }

    public BookEntity map(Book book) {
        return new BookEntity(
                book.getIsbn(),
                book.getAuthor(),
                book.getTitle(),
                book.getType()
        );
    }
}
