package org.ipap.scleaner.model.area;

import lombok.Value;

/**
 * Represents a pair of coordinates in the Cartesian system
 */
@Value
public class Coordinates {

    /**
     * Distance from X-axis
     */
    int x;
    /**
     * Distance from Y-axis
     */
    int y;
}
