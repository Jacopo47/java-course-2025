package org.course.models.http.responses;

import org.course.models.data.Order;

import java.util.List;

public record OrdersByUser(List<Order> orders) { }
