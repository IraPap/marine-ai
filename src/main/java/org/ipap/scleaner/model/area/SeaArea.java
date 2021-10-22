package org.ipap.scleaner.model.area;

import org.ipap.scleaner.model.surface.SeaSurface;
import org.ipap.scleaner.model.surface.Surface;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a rectangle area at sea
 */
public class SeaArea {

    @Getter
    private final Map<Coordinates, Surface> gridLocations;

    public SeaArea(int width, int height) {
        this.gridLocations = createGridLocations(width, height);
    }

    /**
     * Creates a grid of 1x1 squares inside the rectangle area
     *
     * @param width  the rectangle's width
     * @param height the rectangle's height
     * @return a grid map with keys = the X,Y coordinates identifying the bottom left corner of a grid position and values = the {@link Surface} of that grid position
     */
    private Map<Coordinates, Surface> createGridLocations(int width, int height) {

        Map<Coordinates, Surface> result = new HashMap<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result.put(new Coordinates(i, j), new SeaSurface());
            }
        }
        return result;
    }
}
