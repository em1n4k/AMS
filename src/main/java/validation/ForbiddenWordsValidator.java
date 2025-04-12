package validation;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class ForbiddenWordsValidator implements ConstraintValidator<ForbiddenWords, String >{

    private List<String> forbiddenWords;

    @Override
    public void initialize(ForbiddenWords constraintAnnotation) {
        try {
            forbiddenWords = Files.readAllLines(Paths.get("src/main/resources/forbidden-words.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки списка некорректных слов",e);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return forbiddenWords.stream().noneMatch(word -> Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(value).find());
    }
}
