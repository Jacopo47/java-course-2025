package org.course.models.http.requests;

import java.util.List;

public record CreateOrder(
        long userId,
        List<BookOrder> books
) { }
