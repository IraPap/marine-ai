package org.ipap.scleaner.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartingPositionValidator.class)
@Documented
public @interface StartingPosition {

    String message() default "{startingPosition must be a list of two integers >= 0 and each of them has to be less than the respective areaSize integer}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
