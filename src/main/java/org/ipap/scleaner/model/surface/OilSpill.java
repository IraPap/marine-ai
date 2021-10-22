package org.ipap.scleaner.model.surface;

/**
 * Represents an oil spill
 */
public interface OilSpill {

    void fillWithOil();

    boolean hasOil();

    void clean();
}
