package models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validStudent() {
        Student student = new Student(
                "Алексей", "Попович", "Реклама и связи с общественностью", AttendanceStatus.PRESENT, "1 Курс", 20, "alexpopa@example.com", "+79999415123", 1L
        );
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidPhoneNumber() {
        Student student = new Student(
                "Алексей", "Попович", "Реклама и связи с общественностью", AttendanceStatus.PRESENT, "1 Курс", 20, "alexpopa@example.com", "invalid", 1L
        );
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertFalse(violations.isEmpty());

    }

    @Test
    void invalidEmail() {
        Student student = new Student(
                "Алексей", "Попович", "Реклама и связи с общественностью", AttendanceStatus.PRESENT, "1 Курс", 20, "invalid", "+79999415123", 1L
        );
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertFalse(violations.isEmpty());
        assertEquals("Формат: invalid@email.com", violations.iterator().next().getMessage());
    }
}