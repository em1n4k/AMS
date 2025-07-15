package validation;

import java.time.LocalDate;
import java.time.Period;

import static validation.ValidationRules.*;

/**
 * Abstract base class for all model validators.
 *
 * @param <T> | the type of model this validator works with
 */
public abstract class AbstractValidator<T> {

    /**
     * Validates the give model object and returns a ValidationResult containing validation errors, if there are any of them.
     *
     * @param model | the model object to validate
     * @return ValidationResult containing validation outcome
     */
    public abstract ValidationResult validate(T model);


    /**
     * Checks if a string is not null and not blank (after trimming).
     *
     * @param str | the string to check
     * @return true | if the string is not null and not blank
     */
    protected boolean notBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }


    /**
     * Checks if an integer value is within a specified range (inclusive)
     *
     * @param value | the number to check
     * @param min | the minimum allowed value
     * @param max | the maximum allowed value
     * @return true | if value is between min and max, inclusive
     */
    protected boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Very basic email format check (for MVP).
     *
     * @param email | the email string to check
     * @return true | if email is not null and contains '@'
     */
    protected boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    protected void validateBirthDate(LocalDate birthDate, ValidationResult result, int minAge, int maxAge) {
        if (birthDate == null) {
            result.addError("Birth Date is required.");
        } else if (birthDate.isAfter(LocalDate.now())) {
            result.addError("Birt date can't be in the future.");
        } else {
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            if (age < minAge) {
                result.addError("Age must be between " + minAge + " and " + maxAge);
            } else if (age > maxAge) {
                result.addError("Age must be between " + maxAge + " and " + minAge);
            }
        }
    }
}
