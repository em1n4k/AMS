package validation;

import models.Admin;

public class AdminValidator extends AbstractValidator<Admin> {

    @Override
    public ValidationResult validate (Admin admin) {
        ValidationResult result = new ValidationResult();

        if (!notBlank(admin.getUsername())) {
            result.addError("Username is required.");
        }

        return result;
    }
}
