package org.course;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.course.configuration.WithPostgresDatasource;
import org.course.controllers.BookController;
import org.course.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@QuarkusTest
@QuarkusTestResource(value = WithPostgresDatasource.class, restrictToAnnotatedClass = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTests {

    @Inject
    private BookController controller;

    @Test
    void getBooks_BasicScenario() {
        var result = controller.search();

        Assertions.assertFalse(result.isEmpty());
    }

}
