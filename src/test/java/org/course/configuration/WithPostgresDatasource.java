package org.course.configuration;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class WithPostgresDatasource implements QuarkusTestResourceLifecycleManager {


    private final PostgreSQLContainer<?> postgres;

    public WithPostgresDatasource() {
        this.postgres = new PostgreSQLContainer<>()
                .withUsername("user-for-book-store")
                .withPassword("asecretpassword")
                .withDatabaseName("bookstore");
    }

    @Override
    public Map<String, String> start() {
        postgres.start();



        return Map.ofEntries(
                Map.entry("quarkus.datasource.db-kind", "oracle"),
                Map.entry("quarkus.datasource.jdbc.url", postgres.getJdbcUrl()),
                Map.entry("quarkus.datasource.username", postgres.getUsername()),
                Map.entry("quarkus.datasource.password", postgres.getPassword()));
    }

    @Override
    public void stop() {
        postgres.stop();
    }
}
