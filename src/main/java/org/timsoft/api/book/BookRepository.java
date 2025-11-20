package org.timsoft.api.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.cache.annotation.CacheResult;
import javax.enterprise.context.ApplicationScoped;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository class for Book entity operations. Provides data access methods using JPA
 * EntityManager.
 */
@ApplicationScoped
@Slf4j
public class BookRepository {

  private List<Book> books;

  @PostConstruct
  public void init() {
    books = new ArrayList<>();
    books.add(new Book(1L, "1984xx", "George Orwell"));
    books.add(new Book(2L, "To Kill a Mockingbird", "Harper Lee"));
    books.add(new Book(3L, "The Great Gatsby", "F. Scott Fitzgerald"));
  }

  @CacheResult(cacheName = "cache1Min")
  public List<Book> findAll() {
    log.info(">>> REPOSITORY: Fetching all books from database/repository <<<");
    return books;
  }

  public Optional<Book> findById(Long id) {
    return books.stream().filter(book -> book.getId().equals(id)).findFirst();
  }

  public Book create(Book book) {
    books.add(book);
    return book;
  }

  public Book update(Book book) {
    Optional<Book> existingBook = findById(book.getId());
    if (existingBook.isPresent()) {
      books.remove(existingBook.get());
      books.add(book);
      return book;
    }
    return null;
  }

  public boolean delete(Long id) {
    Optional<Book> existingBook = findById(id);
    if (existingBook.isPresent()) {
      books.remove(existingBook.get());
      return true;
    }
    return false;
  }
}
