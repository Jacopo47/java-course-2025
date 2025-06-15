package org.course.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.User;
import org.course.models.http.requests.UserRegistration;
import org.course.services.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Map;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService service;

    @GET
    public User get() {
        return service.byEmail(jwt.getSubject());
    }

    @POST
    public User create(UserRegistration registration) {
        return service.create(registration);

    }
}
