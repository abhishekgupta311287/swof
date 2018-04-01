package com.swof.impl.service;

import com.swof.interfaces.IEngineerPool;
import com.swof.interfaces.IRuleEvaluator;
import com.swof.interfaces.IScheduleStrategy;
import com.swof.model.Engineer;
import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * Rather than trying to find the best candidate for a fixed slot, this algorithm
 * picks a random engineer then walks through the slots that havn't been filled to look
 * for a suitable one.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NextSlotScheduleStrategy implements IScheduleStrategy {
    private IRuleEvaluator ruleEvaluator;

    @Autowired
    public NextSlotScheduleStrategy(IRuleEvaluator ruleEvaluator) {
        this.ruleEvaluator = ruleEvaluator;
    }

    public final ArrayList<Shift> generate(IEngineerPool engineerPool, int shiftsPerPeriod) {
        // Populate the shift schedule without engineers
        ArrayList<Shift> shifts = new ArrayList<>(shiftsPerPeriod);
        for (int i = 0; i < shiftsPerPeriod; i++) {
            shifts.add(new Shift(i));
        }

        // Find an engineer and then walk through the shifts to
        // find the next valid one
        Engineer engineer;
        while ((engineer = engineerPool.getRandomEngineer()) != null) {
            for (int i = 0; i < shiftsPerPeriod; i++) {
                Engineer shiftEngineer = shifts.get(i).getEngineer();
                if (shiftEngineer == null) {
                    boolean isValid = ruleEvaluator.isValid(i, engineer.getId(), shifts);
                    if (isValid) {
                        shifts.get(i).setEngineer(engineer);
                        engineerPool.removeEngineer(engineer);
                        break;
                    }
                }
            }
        }

        return shifts;
    }
}
