package org.course.models.http.requests;

import org.course.models.data.interfaces.Auditable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateOrder(
        long id,
        long userId,
        long bookId,
        long quantity,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Auditable {
}
