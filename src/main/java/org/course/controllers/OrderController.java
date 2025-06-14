package org.course.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.Order;
import org.course.models.http.requests.CreateOrder;
import org.course.models.http.responses.OrdersByUser;
import org.course.services.OrderService;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService service;

    @GET
    public OrdersByUser all() {
        return new OrdersByUser(service.search(0));
    }


    /* ATTENTION: here we have a HUGE security bug!! */
    @GET
    @Path("/{id}")
    public Order getById(long id) {
        return service.byId(id);
    }


    @POST
    public Order create(CreateOrder body) {
        return null;
    }

}
