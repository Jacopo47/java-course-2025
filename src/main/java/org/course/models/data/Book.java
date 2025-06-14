package org.course.models.data;

import org.course.models.data.interfaces.Auditable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Book(
        long id,
        String title,
        String isbn,
        LocalDate publication,
        BigDecimal price,
        long author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Auditable { }
