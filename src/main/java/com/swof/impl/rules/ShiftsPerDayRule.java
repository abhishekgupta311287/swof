package com.swof.impl.rules;


import com.swof.interfaces.IRule;
import com.swof.model.Engineer;
import com.swof.model.Shift;

import java.util.ArrayList;

/**
 * An engineer can do at most one half day shift in a day
 */import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ShiftsPerDayRule implements IRule {
    @Autowired
    public ShiftsPerDayRule() {
    }

    public final boolean isValid(int shiftId, int candidateId, ArrayList<Shift> shifts) {
        // If there are currently no shifts then this proposal is valid
        if (getCount(shifts) == 0) {
            return true;
        } else if (shiftId % 2 == 1) {
            Engineer engineer = shifts.get(shiftId - 1).getEngineer();
            //Its an afternoon shift, so check the morning is not the same enginner
            return !(engineer != null && engineer.getId() == candidateId);
        } else {
            //Proposed shift is for a morning, we only check when populating the afternoon shift
            return true;
        }
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
