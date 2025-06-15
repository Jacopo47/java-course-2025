package org.course.models.http.responses;

import org.course.models.data.OrderItem;

import java.util.List;

public record OrdersByUser(List<OrderItem> orders) { }
