package com.swof.interfaces;

import com.swof.model.Shift;

import java.util.ArrayList;


public interface IRule {
    /**
     * Determines if the rule is valid given the passed parameters
     *
     * @param shiftId     Identifier of the proposed shift
     * @param engineerId Identifier of the proposed candiate
     * @param shifts      Curent schedule to shifts
     * @return True if rule passed against passed defined set of rules
     */
    boolean isValid(int shiftId, int engineerId, ArrayList<Shift> shifts);
}
