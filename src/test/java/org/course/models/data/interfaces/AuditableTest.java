package org.course.models.data.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

record TestAuditable(LocalDateTime createdAt, LocalDateTime updatedAt) implements Auditable { }

class AuditableTest {

    @Test
    public void isUpdatedAfterCreation() {
        var now = LocalDateTime.now();
        var first = new TestAuditable(now, now);
        Assertions.assertFalse(first.hasReceivedUpdates());

        var second = new TestAuditable(now, LocalDateTime.now());
        Assertions.assertTrue(second.hasReceivedUpdates());
    }

    @Test
    public void streamExample() {
        var pipeline = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .stream()
                .map(e -> e * 5)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .map(Object::toString);

        System.out.println(pipeline.collect(Collectors.toUnmodifiableList()));
        System.out.println(pipeline.count());
    }


    @Test
    public void optionalExample() {

        Function<String, Optional<String>> toUpper = input -> Optional.ofNullable(input)
                .map(String::toUpperCase);

        var person = new Person("Jacopo", new Address("street"));
        var street = Optional.ofNullable(person)
                .map(Person::address)
                .map(Address::street)
                .filter(e -> !e.isEmpty())
                .flatMap(toUpper)
                .orElse("unknown");

        Assertions.assertEquals("STREET", street);

        if (person != null && person.address() != null && person.address().street() != null) {
            if (!person.address().street().isEmpty()) {
                street = person.address().street().toUpperCase();
            }
        }
    }

    @Test
    void patternMatching() {
        Object input = "hello";
        var result = switch (input) {
            case Integer i -> "It's and integer";
            case Long l -> "It's a long";
            case Double d -> "It's a double";
            case String s when s.isEmpty() -> "It's an empty string";
            case String s -> s;
            default -> "Unexpected value: " + input;
        };

        Assertions.assertEquals("hello", result);
    }

}

record Person(String name, Address address) {}

record Address(String street) {}