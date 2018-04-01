package com.swof.interfaces;

import com.swof.model.Shift;

import java.util.ArrayList;


public interface IRule {
    /**
     * Determines if the rule is valid given the passed parameters
     *
     * @param shiftId     Identifier of the proposed shift
     * @param candidateId Identifier of the proposed candiate
     * @param shifts      Curent schedule to shifts
     * @return True if rule passed against passed criteria
     */
    boolean isValid(int shiftId, int candidateId, ArrayList<Shift> shifts);
}
