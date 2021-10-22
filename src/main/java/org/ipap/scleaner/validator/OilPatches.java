package org.ipap.scleaner.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OilPatchesValidator.class)
@Documented
public @interface OilPatches {

    String message() default "{oilPatches must be a list of lists, each nested list must be a pair of two integers >= 0 and each of them has to be less than the respective areaSize integer, no two pairs can be the same}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
