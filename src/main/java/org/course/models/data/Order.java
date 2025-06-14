package org.course.models.data;

import org.course.models.data.interfaces.Auditable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Order(
        long id,
        long userId,
        long bookId,
        int quantity,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Auditable { }

