package org.timsoft.api.book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;

/**
 * Entity class representing a Book in the bookstore. Uses Lombok annotations to reduce boilerplate
 * code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

  @Id
  private Long id;

  // @NotBlank(message = "Title is required and cannot be blank")
  @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
  private String title;

  // @NotBlank(message = "Author is required and cannot be blank")
  @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
  private String author;

  /**
   * Constructor without ID (for creating new books).
   *
   * @param title the book title
   * @param author the book author
   */
  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }
}
