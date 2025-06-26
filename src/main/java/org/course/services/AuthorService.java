package org.course.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.course.models.data.Author;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@ApplicationScoped
public class AuthorService {

    private static final long DEFAULT_OFFSET = 0;
    private static final long DEFAULT_LIMIT = 10;
    private static final String SELECT_FROM_AUTHORS = """
            SELECT *
            FROM Authors
            WHERE 1=1
            LIMIT :limit
            OFFSET :offset
            """;

    private static final String SELECT_FROM_AUTHORS_BY_NAME= """
            SELECT *
            FROM Authors
            WHERE name=':name'
            LIMIT :limit
            OFFSET :offset
            """;
    @Inject
    Jdbi db;

    public List<Author> search() {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_AUTHORS)
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .mapTo(Author.class)
                .list());
    }

    public List<Author> search(String name) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_AUTHORS_BY_NAME
                        .replace(":name", name))
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .mapTo(Author.class)
                .list());
    }

}
