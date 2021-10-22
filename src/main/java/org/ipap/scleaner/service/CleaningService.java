package org.ipap.scleaner.service;

import org.ipap.scleaner.model.RoboticCleaner;
import org.ipap.scleaner.model.area.Coordinates;
import org.ipap.scleaner.model.area.Direction;
import org.ipap.scleaner.model.area.SeaArea;
import org.ipap.scleaner.model.input.Instructions;
import org.ipap.scleaner.model.output.CleaningResult;
import org.ipap.scleaner.model.surface.Surface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CleaningService {


    /**
     * Deploys a {@link RoboticCleaner} inside a new {@link SeaArea} and moves it towards the given {@link Direction}, attempting to clean any oil patches it encounters
     *
     * @param instructions the {@link Instructions} with all the information to create the area, fill the oil patches, deploy the cleaner at a starting position and move it along a path
     * @return the {@link CleaningResult} with the final position of the cleaner and the number of oil patches cleaned
     */
    public CleaningResult cleanArea(Instructions instructions) {

        Map<Coordinates, Surface> areaGrid = createAreaGrid(instructions.getAreaSize(), instructions.getOilPatches());
        RoboticCleaner roboticCleaner = new RoboticCleaner(new Coordinates(instructions.getStartingPosition().get(0), instructions.getStartingPosition().get(1)));

        Surface startingLocation = areaGrid.get(roboticCleaner.getCurrentPosition());
        roboticCleaner.checkForOilAndClean(startingLocation);

        for (Direction direction : getDirectionsForMovement(instructions.getNavigationInstructions())) {

            Surface newLocation = roboticCleaner.moveInsideGrid(direction, areaGrid);
            roboticCleaner.checkForOilAndClean(newLocation);
        }

        return new CleaningResult(roboticCleaner.getCurrentPosition(), roboticCleaner.getOilPatchesCleaned());

    }

    /**
     * Creates a map of {@link Coordinates} keys and {@link Surface} values representing the grid locations inside the sea area, then fills the appropriate locations with oil
     *
     * @param areaSize   the rectangle area size boundaries given as a list of integers
     * @param oilPatches the locations of one or more oil patches inside the area, given as a list of integers
     * @return a map of {@link Coordinates} keys and {@link Surface} values
     */
    private Map<Coordinates, Surface> createAreaGrid(List<Integer> areaSize, List<List<Integer>> oilPatches) {

        SeaArea seaArea = new SeaArea(areaSize.get(0), areaSize.get(1));
        Map<Coordinates, Surface> gridLocations = seaArea.getGridLocations();

        List<Coordinates> oilSpills = oilPatches.stream().map(list -> new Coordinates(list.get(0), list.get(1))).collect(Collectors.toList());
        oilSpills.forEach(coordinates -> gridLocations.get(coordinates).fillWithOil());

        return gridLocations;
    }

    /**
     * Parses the navigationInstructions string into a list of {@link Direction}
     *
     * @param navigationInstructions a string sequence of chars "N", "S", "W", "E" representing the four cardinal directions
     * @return a list of {@link Direction}
     */
    private List<Direction> getDirectionsForMovement(String navigationInstructions) {

        List<String> directionInstructions = List.of(navigationInstructions.split(""));
        return directionInstructions.stream().map(Direction::valueOfLabel).collect(Collectors.toList());
    }
}
