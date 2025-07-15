package validation;

import java.util.List;
import java.util.ArrayList;

public class ValidationResult {

    private List<String> errors;

    // No-arg constructor initializes the empty error list
    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    // Add a new error message
    public void addError(String errorMessage) {
        errors.add(errorMessage);
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
