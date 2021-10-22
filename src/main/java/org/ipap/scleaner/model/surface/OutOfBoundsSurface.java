package org.ipap.scleaner.model.surface;

/**
 * Represents a surface outside the grid of movement
 */
public class OutOfBoundsSurface extends Surface {

    public OutOfBoundsSurface() {
        this.status = SurfaceStatus.OUT_OF_BOUNDS;
    }

    @Override
    public void fillWithOil() {
        // Should not fill with oil outside the grid of movement
    }

    @Override
    public boolean hasOil() {
        return false;
    }

    @Override
    public void clean() {
        // Should not clean outside the grid of movement
    }
}
