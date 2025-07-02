package org.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

public class OptionalTest {

    @Test
    void example() {
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
    }

    record Person(String name, Address address) {}

    record Address(String street) {}


}
