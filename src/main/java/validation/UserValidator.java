package validation;

import models.User;

public class UserValidator extends AbstractValidator<User> {

    @Override
    public ValidationResult validate(User user) {
        ValidationResult result = new ValidationResult();
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            result.addError("Email is required");
        }
        if (user.getPasswordHash() == null || user.getPasswordHash().isBlank()) {
            result.addError("Password hash is required");
            }
        return result;
    }
}

