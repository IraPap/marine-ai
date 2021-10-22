package org.ipap.scleaner.model.surface;

/**
 * Represents a sea surface on the grid the cleaner moves in
 */
public class SeaSurface extends Surface {

    public SeaSurface() {
        this.status = SurfaceStatus.CLEAN;
    }

    /**
     * Pollutes a sea surface with oil
     */
    @Override
    public void fillWithOil() {
        this.status = SurfaceStatus.OIL_POLLUTED;
    }

    /**
     * Checks if the sea surface is polluted with oil
     *
     * @return true if polluted, false otherwise
     */
    @Override
    public boolean hasOil() {
        return this.status == SurfaceStatus.OIL_POLLUTED;
    }

    /**
     * Cleans a sea surface
     */
    @Override
    public void clean() {
        this.status = SurfaceStatus.CLEAN;
    }
}
