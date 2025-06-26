package org.course.models.http.requests;

import java.time.LocalDate;

public record UserRegistration(
        String email,
        LocalDate birthdate,
        String surnme
) { }
