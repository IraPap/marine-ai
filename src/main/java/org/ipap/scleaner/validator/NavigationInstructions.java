package org.ipap.scleaner.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NavigationInstructionsValidator.class)
@Documented
public @interface NavigationInstructions {

    String message() default "{navigationInstructions must be a non-empty String consisted only of chars 'N', 'S', 'E' and 'W'}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
