package com.swof.interfaces;

import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * Provides a way for generation of a schedule
 */
public interface IScheduleGenerator
{
    /**
     Generates a new schedule with the specified parameters

     @param shiftsPerPeriod The number of shifts per period
     @param shiftsPerEngineerPerPeriod The number of shifts per engineer per period
     @return Ordered list of shifts
     */
    ArrayList<Shift> generate(int shiftsPerPeriod, int shiftsPerEngineerPerPeriod);
}
