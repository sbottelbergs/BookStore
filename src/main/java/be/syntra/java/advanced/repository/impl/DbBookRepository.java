package be.syntra.java.advanced.repository.impl;

import be.syntra.java.advanced.entity.BookEntity;
import be.syntra.java.advanced.entity.BookMapper;
import be.syntra.java.advanced.model.Book;
import be.syntra.java.advanced.model.BookType;
import be.syntra.java.advanced.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class DbBookRepository implements BookRepository {
    private final EntityManagerFactory emf;
    private final BookMapper mapper;

    public DbBookRepository(EntityManagerFactory emf) {
        this.emf = emf;
        this.mapper = new BookMapper();
    }

    @Override
    public void addBook(Book book) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            BookEntity bookEntity = mapper.map(book);
            em.persist(bookEntity);
            tx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            BookEntity bookEntity = em.find(BookEntity.class, isbn);
            return bookEntity != null ? mapper.map(bookEntity) : null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void removeBook(Book book) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            BookEntity bookEntity = mapper.map(book);
            bookEntity = em.merge(bookEntity);
            em.remove(bookEntity);
            tx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Book> getBooksByType(BookType type) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            TypedQuery<BookEntity> query =
                    em.createQuery(
                            "SELECT b FROM BookEntity b WHERE b.type = '" + type.toString() + "'",
                            BookEntity.class
                    );
            List<BookEntity> entities = query.getResultList();
            return entities.stream().map(mapper::map).collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            TypedQuery<BookEntity> query =
                    em.createQuery("SELECT b FROM BookEntity b", BookEntity.class);
            List<BookEntity> entities = query.getResultList();
            return entities.stream().map(mapper::map).collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
