package org.ipap.scleaner.model.input;

import org.ipap.scleaner.validator.AreaSize;
import org.ipap.scleaner.validator.NavigationInstructions;
import org.ipap.scleaner.validator.OilPatches;
import org.ipap.scleaner.validator.StartingPosition;
import org.ipap.scleaner.validator.group.BasicInfo;
import org.ipap.scleaner.validator.group.ComplexInfo;
import lombok.Builder;
import lombok.Value;

import javax.validation.GroupSequence;
import java.util.List;

/**
 * Represents the input instructions
 */
@Value
@Builder
@GroupSequence({BasicInfo.class, ComplexInfo.class, Instructions.class})
@StartingPosition(groups = ComplexInfo.class)
@OilPatches(groups = ComplexInfo.class)
public class Instructions {

    /**
     * A pair of sea area dimensions as X and Y coordinates, both > 0, identifying the top right corner of a rectangle.
     */
    @AreaSize(groups = BasicInfo.class)
    List<Integer> areaSize;

    /**
     * A pair of X and Y coordinates, within areaSize bounds, identifying the cleaner initial position.
     */
    List<Integer> startingPosition;

    /**
     * A list of pairs of X and Y coordinates, within areaSize bounds, identifying the patches of oil.
     */
    List<List<Integer>> oilPatches;

    /**
     * A string sequence of chars "N", "S", "W" and "E" identifying the navigation of the cleaner in the four Cardinal directions
     */
    @NavigationInstructions(groups = BasicInfo.class)
    String navigationInstructions;
}
