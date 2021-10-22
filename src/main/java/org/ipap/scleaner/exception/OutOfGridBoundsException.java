package org.ipap.scleaner.exception;


import org.ipap.scleaner.model.area.Coordinates;
import org.ipap.scleaner.model.area.Direction;

/**
 * Runtime exception to be used when cleaner moves out of bounds
 */
public class OutOfGridBoundsException extends RuntimeException {


    public OutOfGridBoundsException(Coordinates fromPosition, Direction direction) {
        super(String.format("navigationInstructions navigated the Cleaner out of bounds by moving %s from %s", direction.label, fromPosition.toString()));
    }
}
