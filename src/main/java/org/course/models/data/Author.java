package org.course.models.data;

import java.time.LocalDate;

public record Author(long id, String name, String surname, LocalDate birthdate) { }
