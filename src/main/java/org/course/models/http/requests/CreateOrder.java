package org.course.models.http.requests;

import java.util.List;

public record CreateOrder(
        String name,
        List<Long> bookIds
) { }
