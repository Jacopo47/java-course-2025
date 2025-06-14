package org.course.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.course.models.data.Author;
import org.course.models.data.Book;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@ApplicationScoped
public class BookService {

    private static final long DEFAULT_OFFSET = 0;
    private static final long DEFAULT_LIMIT = 10;
    private static final String SELECT_FROM_AUTHORS = """
            SELECT *
            FROM Books
            WHERE 1=1
            <if(author)> AND author=:author <endif>
            LIMIT :limit
            OFFSET :offset
            """;
    @Inject
    Jdbi db;

    public List<Book> byAuthor(long id) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_AUTHORS)
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .bind("author", id)
                .defineNamedBindings() /* Calling this method is useful in order to bind all query's vars. Useful in case of conditional <if(..)> <endif> in the query. */
                .mapTo(Book.class)
                .list());
    }

    public List<Book> search(
    ) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_AUTHORS)
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .mapTo(Book.class)
                .list());
    }

}
