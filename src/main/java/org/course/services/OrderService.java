package org.course.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.course.models.data.Order;
import org.course.models.http.requests.CreateOrder;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@ApplicationScoped
public class OrderService {

    private static final long DEFAULT_OFFSET = 0;
    private static final long DEFAULT_LIMIT = 10;
    private static final String SELECT_FROM_ORDERS = """
            SELECT *
            FROM Orders
            WHERE user=:userId
            LIMIT :limit
            OFFSET :offset
            """;

    private static final String SELECT_FROM_ORDERS_BY_ID = """
            SELECT *
            FROM Orders
            WHERE id=:id
            """;

    private static final String INSERT = """
            INSERT INTO orders (id, userId, bookId, quantity, price, createdAt, updatedAt)
                VALUES(:id, :userId, :bookId, :quantity, :price, :createdAt, :updatedAt);
            """;
    @Inject
    Jdbi db;

    public List<Order> search(long userId) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_ORDERS)
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .bind("userId", userId)
                .mapTo(Order.class)
                .list());
    }

    public Order byId(long id) {

        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_ORDERS_BY_ID)
                .bind("id", id)
                .mapTo(Order.class)
                .one());
    }

    public Order create(CreateOrder request) {

        return db.withHandle(handle -> handle
                .createQuery(INSERT)
                .bindBean(request)
                .mapTo(Order.class)
                .one());
    }

}
