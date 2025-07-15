package validation;

import models.Teacher;
import java.util.List;
import java.util.ArrayList;

public class TeacherValidator extends AbstractValidator<Teacher> {

    @Override
    public ValidationResult validate(Teacher teacher) {
        ValidationResult result = new ValidationResult();

        if (!notBlank(teacher.getFirstName())) {
            result.addError("First name is required.");
        }

        if (!notBlank(teacher.getLastName())) {
            result.addError("Last name is required.");
        }

        if (!notBlank(teacher.getPatronymic())) {
            result.addError("Patronymic is required.");
        }

        // Birth Date validation
        validateBirthDate(
                teacher.getBirthDate(),
                result,
                ValidationRules.TEACHER_MIN_AGE,
                ValidationRules.TEACHER_MAX_AGE
        );

        if (!isValidEmail(teacher.getEmail())) {
            result.addError("Email is not valid.");
        }

        if (!notBlank(teacher.getPhoneNumber())) {
            result.addError("Phone number is required.");
        }

        return result;
    }
}
