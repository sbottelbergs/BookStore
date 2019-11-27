package be.syntra.java.advanced.entity;

import be.syntra.java.advanced.model.BookType;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class BookEntity {
    @Id
    private int isbn;
    private String author;
    private String title;
    @Enumerated(EnumType.STRING)
    private BookType type;

    public BookEntity() {
    }

    public BookEntity(int isbn, String author, String title, BookType type) {
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

}
