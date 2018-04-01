package com.swof.interfaces;

import com.swof.model.Engineer;

import java.util.ArrayList;

/**
 * Provides methods to manipulate a pool of Engineers
 */
public interface IEngineerPool {
    /**
     * Adds a list of engineers to the pool
     *
     * @param engineers
     */
    void addEngineers(ArrayList<Engineer> engineers);

    /**
     * Retrieves an engineer from pool at random
     *
     * @return
     */
    Engineer getRandomEngineer();

    /**
     * Removes the specified engineer from the pool
     *
     * @param engineer The engineer to be removed from the pool
     */
    void removeEngineer(Engineer engineer);

    /**
     * Resets the list of engineers that can be pulled to the available list of engineers
     */
    void resetPullableEngineers();

    /**
     * Gets the number of engineers available
     */
    int getAvailableEngineers();

    /**
     * Gets the number of engineers that have not yet been pulled
     */
    int getPullableEngineers();
}
