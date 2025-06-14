package org.course.models.data;

import org.course.models.data.interfaces.Auditable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Author(
        long id,
        String name,
        String surname,
        LocalDate birthdate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Auditable { }
