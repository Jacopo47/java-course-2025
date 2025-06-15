package org.course.controllers;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.course.models.data.User;
import org.course.models.http.requests.Login;
import org.course.models.http.requests.UserRegistration;
import org.course.models.http.responses.Token;
import org.course.services.UserService;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;
import java.util.Arrays;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    UserService service;


    @POST
    @Path("/login")
    public Token login(Login login) {
        var user = service.byEmail(login.email());

        var token = Jwt
                        .subject(user.email())
                        .claim(Claims.birthdate.name(), user.birthdate())
                        .expiresIn(Duration.ofHours(1))
                        .sign();


        return new Token(token);
    }
}
