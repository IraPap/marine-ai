package org.ipap.scleaner.model.output;

import org.ipap.scleaner.model.area.Coordinates;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the result of cleaning the sea area
 */
@Value
public class CleaningResult {

    /**
     * The final position of the cleaner
     */
    List<Integer> finalPosition;
    /**
     * Number of oil patches cleaned in the process
     */
    int oilPatchesCleaned;

    public CleaningResult(Coordinates coordinates, int oilPatchesCleaned) {
        this.finalPosition = Arrays.asList(coordinates.getX(), coordinates.getY());
        this.oilPatchesCleaned = oilPatchesCleaned;
    }
}
