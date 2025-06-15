package org.course.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.User;
import org.course.models.http.requests.CreateOrder;
import org.course.models.http.responses.OrdersByUser;
import org.course.services.OrderService;
import org.course.services.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    JsonWebToken jwt;

    @Inject
    OrderService service;

    @Inject
    UserService userService;


    @GET
    public OrdersByUser all() {
        return Optional.ofNullable(jwt)
                .map(JsonWebToken::getSubject)
                .map(userService::byEmail)
                .map(User::id)
                .map(service::search)
                .map(OrdersByUser::new)
                .orElseThrow();
    }


    /* ATTENTION: here we have a HUGE security bug!! */
    @GET
    @Path("/{id}")
    public OrdersByUser getById(long id) {
        return Optional.ofNullable(service.byId(id))
                .map(OrdersByUser::new)
                .orElseThrow();
    }


    /* ATTENTION: as above for the .getById(..) method we have the same bug with high severity! */
    @POST
    public OrdersByUser create(CreateOrder body) {
        return Optional.ofNullable(service.create(body))
                .map(OrdersByUser::new)
                .orElseThrow();
    }

}
