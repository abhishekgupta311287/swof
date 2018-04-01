package com.swof.interfaces;

import com.swof.model.Engineer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Provides a list of Engineers
 */
@Component
public interface IEngineerPoolList {
    /**
     * Reads all the engineers from the predefined list
     *
     * @return List of engineers
     */
    ArrayList<Engineer> getAll();

    /**
     * Adds a new engineer to the list
     *
     * @param engineer The engineer to add
     */
    int add(Engineer engineer);

    /**
     * Removes a specific engineer from the list
     *
     * @param id Id of the engineer to remove
     */
    void remove(int id);

    /**
     * Retrieves an engineer from the list
     *
     * @param id Id of the engineer to retreive
     * @return
     */
    Engineer get(int id);
}
