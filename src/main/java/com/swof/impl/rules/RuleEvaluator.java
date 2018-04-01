package com.swof.impl.rules;

import com.swof.interfaces.IRule;
import com.swof.interfaces.IRuleEvaluator;
import com.swof.model.Shift;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * This class verifies that the generated shift pass all the defined rules
 */
@Component
public class RuleEvaluator implements IRuleEvaluator {
    private ArrayList<IRule> rules = new ArrayList<>();

    @Autowired
    public RuleEvaluator() {
        rules.add(new ShiftsPerDayRule());
        rules.add(new ConsecutiveDayRule());

    }

    public final boolean isValid(int shiftId, int engineerId, ArrayList<Shift> shifts) {
        boolean valid = true;
        // Check if all the rules pass
        for (IRule rule : rules) {
            valid &= rule.isValid(shiftId, engineerId, shifts);
        }

        return valid;
    }
}
