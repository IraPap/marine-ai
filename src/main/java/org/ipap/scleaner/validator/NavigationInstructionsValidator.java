package org.ipap.scleaner.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavigationInstructionsValidator implements ConstraintValidator<NavigationInstructions, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null && !value.isBlank()) {

            Pattern pattern = Pattern.compile("^[NESW]*$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();

        } else {

            return false;
        }
    }
}
