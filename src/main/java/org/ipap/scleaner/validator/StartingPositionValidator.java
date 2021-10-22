package org.ipap.scleaner.validator;

import org.ipap.scleaner.model.input.Instructions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class StartingPositionValidator implements ConstraintValidator<StartingPosition, Instructions> {

    @Override
    public boolean isValid(Instructions instructions, ConstraintValidatorContext context) {

        int x = instructions.getAreaSize().get(0);
        int y = instructions.getAreaSize().get(1);

        List<Integer> startingPosition = instructions.getStartingPosition();

        return startingPosition != null && startingPosition.size() == 2 && startingPosition.get(0) >= 0
                && startingPosition.get(0) < x && startingPosition.get(1) >= 0 && startingPosition.get(1) < y;
    }
}
