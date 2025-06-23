package org.course.models.data.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

record TestAuditable(LocalDateTime createdAt, LocalDateTime updatedAt) implements Auditable { }

class AuditableTest {

    @Test
    public void isUpdatedAfterCreation() {
        var now = LocalDateTime.now();
        var first = new TestAuditable(now, now);
        Assertions.assertFalse(first.hasReceivedUpdates());

        var second = new TestAuditable(now, LocalDateTime.now());
        Assertions.assertTrue(second.hasReceivedUpdates());
    }

}