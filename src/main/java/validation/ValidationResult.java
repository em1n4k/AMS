package validation;

import java.util.List;
import java.util.ArrayList;

public class ValidationResult {

    private List<String> errors;

    // Constructor
    public ValidationResult(List<String> errors) {
        this.errors = errors;
    }

    // Is everything valid?
    public boolean isValid() {
        return errors.isEmpty();
    }

    // The detected errors
    public List<String> getErrors() {
        return errors;
    }
}
