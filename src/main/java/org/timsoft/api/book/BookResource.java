package org.timsoft.api.book;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.timsoft.api.cache.ETag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** REST resource for Book operations. Provides endpoints for book management. */
@RequestScoped
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Api(
    value = "Books",
    tags = {"Books"})
public class BookResource {

  @Inject private BookRepository bookRepository;

  @GET
  @ETag
  @ApiOperation(value = "Get all books", response = Book.class, responseContainer = "List")
  @ApiResponses({
    @ApiResponse(code = 200, message = "List of books"),
    @ApiResponse(code = 304, message = "Not Modified")
  })
  public Response getAllBooks() {
    List<Book> books = bookRepository.findAll();
    return Response.ok(books).build();
  }

  // @GET
  // public Response getOneBooks() {
  //     Book b = new Book();
  //     b.setId(1L);
  //     b.setTitle("Sample Book");
  //     b.setAuthor("Author Name");
  //     return Response.ok(b).build();
  // }
}
