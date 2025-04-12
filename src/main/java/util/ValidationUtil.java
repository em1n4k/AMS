package util;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import java.util.Set;

public final class ValidationUtil {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    private ValidationUtil() {
        throw new UnsupportedOperationException("Утилитарный класс не может быть создан в виде экземпляра класса");
    }

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException (violations.iterator().next().getMessage());
        }
    }
}
