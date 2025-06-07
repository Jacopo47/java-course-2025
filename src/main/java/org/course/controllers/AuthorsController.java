package org.course.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.Book;
import org.course.models.http.responses.Authors;
import org.course.services.AuthorService;
import org.course.services.BookService;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorsController {

    @Inject
    AuthorService service;

    @Inject
    BookService bookService;

    @GET
    public Authors search() {
        var authors = service.search();

        return new Authors(authors);
    }

    @GET
    @Path("{id}/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> books(long id) {
        return bookService.byAuthor(id);
    }
}
