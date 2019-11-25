package be.syntra.java.advanced.model;

import java.util.Objects;

public class Book {
    private int isbn;
    private String author;
    private String title;
    private BookType type;

    public Book(int isbn, String author, String title, BookType type) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.type = type;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public BookType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn &&
                author.equals(book.author) &&
                title.equals(book.title) &&
                type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, author, title, type);
    }

    @Override
    public String toString() {
        return String.format(
                "Author: %s\nTitle: %s\nISBN: %d\nType: %s",
                author,
                title,
                isbn,
                type
        );
    }

    public static final class Builder {
        private int isbn;
        private String author;
        private String title;
        private BookType bookType;

        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setBookType(BookType bookType) {
            this.bookType = bookType;
        }

        public Book build() {
            return new Book(isbn, author, title, bookType);
        }
    }
}
