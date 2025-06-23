package org.course.models.data.interfaces;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime createdAt();
    LocalDateTime updatedAt();

    default boolean hasReceivedUpdates() {
        return updatedAt().isAfter(createdAt());
    }
}
