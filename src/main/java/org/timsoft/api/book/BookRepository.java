package org.timsoft.api.book;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Repository class for Book entity operations. Provides data access methods using JPA EntityManager.
 */
@Stateless
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds all books in the database.
     *
     * @return List of all books
     */
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    /**
     * Finds a book by its ID.
     *
     * @param id the book ID
     * @return Optional containing the book if found, empty otherwise
     */
    public Optional<Book> findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    /**
     * Persists a new book to the database.
     *
     * @param book the book to create
     * @return the persisted book
     */
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    /**
     * Updates an existing book.
     *
     * @param book the book to update
     * @return the updated book
     */
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id the book ID
     * @return true if the book was deleted, false if not found
     */
    public boolean delete(Long id) {
        Optional<Book> book = findById(id);
        if (book.isPresent()) {
            entityManager.remove(book.get());
            return true;
        }
        return false;
    }
}
