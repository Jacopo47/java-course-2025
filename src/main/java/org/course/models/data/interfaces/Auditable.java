package org.course.models.data.interfaces;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime createdAt();
    LocalDateTime updatedAt();
}
