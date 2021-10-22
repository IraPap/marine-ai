package org.ipap.scleaner.model.area;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the four Cardinal directions
 */
public enum Direction {

    NORTH("N", new Coordinates(0, 1)),
    EAST("E", new Coordinates(1, 0)),
    SOUTH("S", new Coordinates(0, -1)),
    WEST("W", new Coordinates(-1, 0));

    private static final Map<String, Direction> BY_LABEL = new HashMap<>();

    static {
        for (Direction d : values()) {
            BY_LABEL.put(d.label, d);
        }
    }

    /**
     * The shift in coordinates a movement in each direction would entail
     */
    @Getter
    private final Coordinates coordinateShift;

    /**
     * A string label
     */
    public final String label;

    /**
     * @param label A string label
     * @return the Direction matching the label
     */
    public static Direction valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }


    Direction(String label, Coordinates coordinateShift) {
        this.label = label;
        this.coordinateShift = coordinateShift;
    }
}
