package org.ipap.scleaner.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AreaSizeValidator.class)
@Documented
public @interface AreaSize {

    String message() default "{areaSize must be a list of two integers > 0}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
