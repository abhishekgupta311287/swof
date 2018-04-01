package com.swof.impl.service;


import com.swof.interfaces.IEngineerPool;
import com.swof.interfaces.IRuleEvaluator;
import com.swof.interfaces.IScheduleStrategy;
import com.swof.model.Engineer;
import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * Attempt to fill the schedule a slot at a time. For each slot, pick an engineer at random and
 * check it is valid. If so fill the slot and move to the next one. If not, pick a different engineer
 * at random from the pool and try that one.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class SequentialFillScheduleStrategy implements IScheduleStrategy {
    private IRuleEvaluator ruleEvaluator;

//    @Autowired
    public SequentialFillScheduleStrategy(IRuleEvaluator ruleEvaluator) {
        this.ruleEvaluator = ruleEvaluator;
    }

    public final ArrayList<Shift> generate(IEngineerPool engineerPool, int shiftsPerPeriod) {
        Engineer engineer;
        ArrayList<Shift> shifts = new ArrayList<>();

        // Walk through the shift slots and find a valid engineer for this slot
        for (int i = 0; i < shiftsPerPeriod; i++) {
            boolean foundSuitableEngineer = false;

            while (!foundSuitableEngineer && ((engineer = engineerPool.getRandomEngineer()) != null)) {
                foundSuitableEngineer = ruleEvaluator.isValid(i, engineer.getId(), shifts);
                if (foundSuitableEngineer) {
                    Shift tempShift = new Shift(i);
                    tempShift.setEngineer(engineer);
                    shifts.add(tempShift);
                    engineerPool.removeEngineer(engineer);
                    engineerPool.resetPullableEngineers();
                }
            }
        }
        return shifts;
    }
}
