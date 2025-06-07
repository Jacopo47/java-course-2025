package org.course.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.Order;
import org.course.models.http.requests.CreateOrder;

import java.util.List;

@Path("/orders")
public class Orders {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> all() {
        return List.of();
    }


    /* ATTENTION: here we have a huge security bug!! */
    @GET
    @Path("/:id")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getById(long id) {
        return new Order();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> create(CreateOrder body) {
        return List.of();
    }

}
