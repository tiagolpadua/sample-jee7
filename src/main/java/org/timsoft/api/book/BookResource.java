package org.timsoft.api.book;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://localhost:8080/sample-jee7/api/books


/**
 * REST resource for Book operations. Provides endpoints for book management.
 */
@RequestScoped
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    /**
     * Gets all books from the database.
     *
     * @return Response containing list of all books
     */
    // @GET
    // public Response getAllBooks() {
    //     List<Book> books = bookRepository.findAll();
    //     return Response.ok(books).build();
    // }

    @GET
    public Response getOneBooks() {
        Book b = new Book();
        b.setId(1L);
        b.setTitle("Sample Book");
        b.setAuthor("Author Name");
        return Response.ok(b).build();
    }
}
