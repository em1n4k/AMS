package validation;

import models.Student;
import java.util.List;
import java.util.ArrayList;

public class StudentValidator extends AbstractValidator<Student> {

    @Override
    public ValidationResult validate(Student student) {
        List<String> errors = new ArrayList<>();

        if (!notBlank(student.getFirstName())) {
            errors.add("First name is required");
        }

        if (!notBlank(student.getLastName())) {
            errors.add("Last name is required");
        }

        if (!notBlank(student.getPatronymic())) {
            errors.add("Patronymic/Full name is required");
        }

        if (!isInRange(student.getAge(), 16, 100)) {
            errors.add("Age must be between 16 and 100");
        }

        if (!isValidEmail(student.getEmail())) {
            errors.add("Email is not valid.");
        }

        if (!notBlank(student.getFacultyNumber())) {
            errors.add("Faculty number is required");
        }

        if (!notBlank(student.getPhoneNumber())) {
            errors.add("Phone number is required");
        }

        return new ValidationResult(errors);
    }
}
