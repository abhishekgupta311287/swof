package com.swof.interfaces;

import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * Provides a strategy for schedule generation
 */
public interface IScheduleStrategy {
    /**
     * Generate the schedule using this strategy and given the specified parameters
     *
     * @param engineerPool    The pool to select engineers from
     * @param shiftsPerPeriod The number of shifts to populate
     * @return Ordered list of shifts
     */
    ArrayList<Shift> generate(IEngineerPool engineerPool, int shiftsPerPeriod);
}
