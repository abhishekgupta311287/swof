package com.swof.impl.rules;


import com.swof.interfaces.IRule;
import com.swof.model.Engineer;
import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * An engineer cannot have half day shifts on consecutive days
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConsecutiveDayRule implements IRule {
    @Autowired
    public ConsecutiveDayRule() {
    }

    public final boolean isValid(int shiftId, int engineerId, ArrayList<Shift> shifts) {
        // If shiftId is the first day then allocation must be valid
        if (shiftId < 2) {
            return true;
        } else {
            boolean isMorning = (shiftId == 0 || shiftId % 2 == 0);
            if (isMorning) {

                Engineer engineer1 = shifts.get(shiftId - 1).getEngineer();
                Engineer engineer2 = shifts.get(shiftId - 2).getEngineer();

                //Proposed shift is for a morning - check the last 2 shifts are not for the same enginner
                return !((engineer1 != null && engineer1.getId() == engineerId) ||
                        (engineer2 != null && engineer2.getId() == engineerId));
            } else {
                Engineer engineer2 = shifts.get(shiftId - 2).getEngineer();
                Engineer engineer3 = shifts.get(shiftId - 3).getEngineer();
                //Proposed shift is for an afternoon - check the previous days shifts
                return !((engineer2 != null && engineer2.getId() == engineerId) ||
                        (engineer3 != null && engineer3.getId() == engineerId));
            }

//            // The same engineer is not defined for the previous day, so the proposal is valid
//            return true;
        }
    }
}
