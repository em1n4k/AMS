package validation;

import models.Student;

public class StudentValidator extends AbstractValidator<Student> {

    @Override
    public ValidationResult validate (Student student) {
        ValidationResult result = new ValidationResult();

        if (!notBlank(student.getFirstName())) {
            result.addError("First name is required.");
        }

        if (!notBlank(student.getLastName())) {
            result.addError("Last name is required.");
        }

        if (!notBlank(student.getPatronymic())) {
            result.addError("Patronymic is required.");
        }

        // Birth Date validation
        validateBirthDate(
                 student.getBirthDate(),
                 result,
                 ValidationRules.STUDENT_MIN_AGE,
                ValidationRules.STUDENT_MAX_AGE
        );

        if (!isValidEmail(student.getEmail())) {
            result.addError("Email is not valid.");
        }

        if (!notBlank(student.getPhoneNumber())) {
            result.addError("Phone number is required.");
        }

        return result;
    }
}