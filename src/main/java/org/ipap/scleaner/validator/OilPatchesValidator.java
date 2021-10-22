package org.ipap.scleaner.validator;

import org.ipap.scleaner.model.input.Instructions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OilPatchesValidator implements ConstraintValidator<OilPatches, Instructions> {

    @Override
    public boolean isValid(Instructions instructions, ConstraintValidatorContext context) {

        int x = instructions.getAreaSize().get(0);
        int y = instructions.getAreaSize().get(1);

        List<List<Integer>> oilPatches = instructions.getOilPatches();

        return oilPatches != null && !oilPatches.isEmpty()
                && oilPatches.stream().allMatch(list -> list != null && list.size() == 2 && list.get(0) >= 0 && list.get(0) < x && list.get(1) >= 0 && list.get(1) < y)
                && oilPatches.stream().distinct().count() == oilPatches.size();
    }
}
