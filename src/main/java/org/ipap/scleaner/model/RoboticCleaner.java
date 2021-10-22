package org.ipap.scleaner.model;

import org.ipap.scleaner.exception.OutOfGridBoundsException;
import org.ipap.scleaner.model.area.Coordinates;
import org.ipap.scleaner.model.area.Direction;
import org.ipap.scleaner.model.surface.OutOfBoundsSurface;
import org.ipap.scleaner.model.surface.Surface;
import org.ipap.scleaner.model.surface.SurfaceStatus;
import lombok.Getter;

import java.util.Map;

/**
 * Represents the Robotic Cleaner
 */
@Getter
public class RoboticCleaner {

    private Coordinates currentPosition;
    private int oilPatchesCleaned;


    public RoboticCleaner(Coordinates currentPosition) {
        this.currentPosition = currentPosition;
        this.oilPatchesCleaned = 0;
    }

    /**
     * Moves the Robotic Cleaner towards the specified Direction inside the grid
     *
     * @param direction     the {@link Direction} the cleaner should move towards
     * @param gridLocations a map of {@link Coordinates} keys and {@link Surface} values representing the grid locations inside the sea area
     * @return the {@link Surface} of the new position after the movement
     * @throws OutOfGridBoundsException if the cleaner moves out of the grid bounds
     */
    public Surface moveInsideGrid(Direction direction, Map<Coordinates, Surface> gridLocations) throws OutOfGridBoundsException {

        Coordinates newCoordinates = new Coordinates((this.currentPosition.getX() + direction.getCoordinateShift().getX()), this.currentPosition.getY() + direction.getCoordinateShift().getY());
        Surface newPosition = gridLocations.getOrDefault(newCoordinates, new OutOfBoundsSurface());
        if (newPosition.getStatus() == SurfaceStatus.OUT_OF_BOUNDS) {
            throw new OutOfGridBoundsException(this.currentPosition, direction);
        }
        this.currentPosition = newCoordinates;
        return newPosition;
    }


    /**
     * Checks if a given surface has oil, cleans it and increases the counter of oil patches cleaned
     *
     * @param surface the given {@link Surface}
     */
    public void checkForOilAndClean(Surface surface) {

        if (surface.hasOil()) {
            surface.clean();
            oilPatchesCleaned++;
        }

    }
}
