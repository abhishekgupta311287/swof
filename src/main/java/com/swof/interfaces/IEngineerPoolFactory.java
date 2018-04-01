package com.swof.interfaces;

/**
 * Provides methods for creation of a pool
 */
public interface IEngineerPoolFactory {
    /**
     * Creates a pool, using the number of shifts per engineer per period
     * figure to determine how many times each engineer is put into the pool
     *
     * @param shiftsPerEngineerPerPeriod The number of shifts per engineer per period
     * @return The created pool
     */
    IEngineerPool create(int shiftsPerEngineerPerPeriod);
}
