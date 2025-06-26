package org.course.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.course.models.data.User;
import org.course.models.http.requests.UserRegistration;
import org.jdbi.v3.core.Jdbi;

@ApplicationScoped
public class UserService {

    private static final String SELECT_BY_EMAIL = """
            SELECT *
            FROM users
            WHERE 1=1
            <if(id)> AND id=:id <endif>
            <if(email)> AND email=:email <endif>
            """;

    private static final String INSERT = """
            INSERT INTO users (email, birthdate, surnme)
                VALUES(:email, :birthdate, :surnme) returning *;
            """;
    @Inject
    Jdbi db;

    public User byEmail(String email) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_BY_EMAIL)
                .bind("email", email)
                .defineNamedBindings()
                .mapTo(User.class)
                .one());
    }

    public User create(UserRegistration registration) {
        return db.withHandle(handle -> handle
                .createQuery(INSERT)
                .bindMethods(registration)
                .mapTo(User.class)
                .one());
    }

}
