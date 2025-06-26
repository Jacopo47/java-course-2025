package org.course.models.data;
import java.time.LocalDate;

/* QUESTION: Why records ? Which are pro and cons. */
public record User(long id, String email, LocalDate birthdate, String surnme) {}
