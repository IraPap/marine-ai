package org.ipap.scleaner.model.surface;

import lombok.Getter;

/**
 * Represents a 1x1 square surface on a grid
 */
public abstract class Surface implements OilSpill {

    @Getter
    SurfaceStatus status;
}
