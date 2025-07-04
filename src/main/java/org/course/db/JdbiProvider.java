package org.course.db;

import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import org.course.models.data.Author;
import org.course.models.data.Book;
import org.course.models.data.OrderItem;
import org.course.models.data.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.stringtemplate4.StringTemplateEngine;

public class JdbiProvider {
    @Inject
    AgroalDataSource ds;

    @Singleton
    @Produces
    public Jdbi jdbi() {
        return Jdbi.create(ds)
                   .installPlugin(new SqlObjectPlugin())
                   .setSqlLogger(new Slf4JSqlLogger())
                   .registerRowMapper(ConstructorMapper.factory(Author.class))
                   .registerRowMapper(ConstructorMapper.factory(User.class))
                   .registerRowMapper(ConstructorMapper.factory(OrderItem.class))
                   .registerRowMapper(ConstructorMapper.factory(Book.class))
                   .setTemplateEngine(new StringTemplateEngine());
    }
}
