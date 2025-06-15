package org.course.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.course.models.data.OrderItem;
import org.course.models.http.requests.BookOrder;
import org.course.models.http.requests.CreateOrder;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class OrderService {

    private static final long DEFAULT_OFFSET = 0;
    private static final long DEFAULT_LIMIT = 10;
    private static final String SELECT_FROM_ORDERS = """
            SELECT *
            FROM Orders
            WHERE userId=:userId
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

    public List<OrderItem> search(long userId) {
        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_ORDERS)
                .bind("limit", DEFAULT_LIMIT)
                .bind("offset", DEFAULT_OFFSET)
                .bind("userId", userId)
                .mapTo(OrderItem.class)
                .list());
    }

    public List<OrderItem> byId(long id) {

        return db.withHandle(handle -> handle
                .createQuery(SELECT_FROM_ORDERS_BY_ID)
                .bind("id", id)
                .mapTo(OrderItem.class)
                .list());
    }

    public List<OrderItem> create(CreateOrder request) {

        BiFunction<Long, CreateOrder, Stream<OrderItem>> mapToOrders = (id, order) -> {
            var userId = order.userId();

            Function<BookOrder, OrderItem> toOrderItem = b -> new OrderItem(id, userId, b.id(), b.quantity(), b.price(), null, null);


            return Optional.of(order) /* QUESTION: here we are not using .ofNullable(..). Why? */
                    .map(CreateOrder::books)
                    .orElseGet(List::of) /* QUESTION: which is the main difference between .orElse(..) and .orElseGet(..) ? */
                    .stream()
                    .map(toOrderItem);
        };


        Function<OrderItem, OrderItem> insert = o -> db.withHandle(handle -> handle
                .createQuery(INSERT)
                .bindBean(request)
                .mapTo(OrderItem.class)
                .one());

        long nextId = db.withHandle(h -> h.createQuery("SELECT nextval('orders_id_sequence')")
                .mapTo(Long.class))
                .one();

        return mapToOrders.apply(nextId, request)
                .map(insert)
                // QUESTION: here the IDE suggest to modify into a .toList(). Pro and cons?
                .collect(Collectors.toUnmodifiableList());
    }

}
