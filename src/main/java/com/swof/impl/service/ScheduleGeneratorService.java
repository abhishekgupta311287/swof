package com.swof.impl.service;


import com.swof.interfaces.IEngineerPool;
import com.swof.interfaces.IEngineerFactory;
import com.swof.interfaces.IScheduleGeneratorService;
import com.swof.interfaces.IScheduleStrategy;
import com.swof.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ScheduleGeneratorService implements IScheduleGeneratorService {
    private IEngineerFactory engineerPoolFactory;
    private IScheduleStrategy scheduleStrategy;

    @Autowired
    public ScheduleGeneratorService(IEngineerFactory engineerPoolFactory, IScheduleStrategy scheduleStrategy) {
        this.engineerPoolFactory = engineerPoolFactory;
        this.scheduleStrategy = scheduleStrategy;
    }

    /**
     * Generates a schedule. Due to nature of the Random engineer picking, it sometimes
     * picks the engineers in such a way that there is no valid way to populate all the shifts
     * In this case we just have another go with a new Random object.
     * For example, an engineer may not get picked until the 19th slot which is valid, but then
     * the same engineer cannot be used in the remaining slot.
     *
     * @param shiftsPerPeriod            Number of shifts per period
     * @param shiftsPerEngineerPerPeriod Number of shifts per engineer per period
     * @return Ordered list of shifts with allocated engineer
     */
    public final ArrayList<Shift> generate(int shiftsPerPeriod, int shiftsPerEngineerPerPeriod) {
        ArrayList<Shift> shifts = new ArrayList<>();
        while (getCount(shifts) != shiftsPerPeriod) {
            // Create a pool of engineers to use for scheduling
            IEngineerPool engineerPool = engineerPoolFactory.create(shiftsPerEngineerPerPeriod);

            // Generate the shift pattern using the provided strategy
            shifts = scheduleStrategy.generate(engineerPool, shiftsPerPeriod);
        }
        return shifts;
    }

    private int getCount(ArrayList<Shift> shifts) {
        int count = 0;
        for (Shift shift : shifts) {
            if (shift.getEngineer() != null) {
                count++;
            }
        }
        return count;
    }
}
