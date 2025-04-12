package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ForbiddenWordsValidator.class)

public @interface ForbiddenWords {
    String message() default "Содержит некорректные выражения";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}