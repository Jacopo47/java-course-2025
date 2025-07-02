package org.course;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.course.configuration.WithPostgresDatasource;
import org.course.controllers.BookController;
import org.course.controllers.LoginController;
import org.course.models.http.requests.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@QuarkusTest
@QuarkusTestResource(value = WithPostgresDatasource.class, restrictToAnnotatedClass = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginControllerIntegrationTests {

    @Inject
    private LoginController controller;

    @Test
    void login_UserNotExists() {
        Assertions.assertThrows(BadRequestException.class, () -> controller.login(new Login("user that does not exists")));
    }

}
