package org.course.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.Book;
import org.course.services.BookService;

import java.util.List;

@Path("/books")
public class BookController {

    @Inject
    BookService service;

    /* ATTENTION: there is an important error in this API. Found it!*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> search() {
        return service.search();
    }
}
