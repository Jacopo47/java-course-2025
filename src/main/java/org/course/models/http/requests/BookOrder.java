package org.course.models.http.requests;

import java.math.BigDecimal;

public record BookOrder(
    long id,
    int quantity,
    BigDecimal price
) { }
