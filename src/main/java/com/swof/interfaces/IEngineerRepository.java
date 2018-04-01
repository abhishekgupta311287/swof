package com.swof.interfaces;

import com.swof.model.Engineer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Provides a repository of Engineers
 */
@Component
public interface IEngineerRepository {
    /**
     * Reads all the engineers from the repository
     *
     * @return List of engineers
     */
    ArrayList<Engineer> getAll();

    /**
     * Adds a new engineer to the repository
     *
     * @param engineer The engineer to add
     */
    int add(Engineer engineer);

    /**
     * Removes a specific engineer from the repository
     *
     * @param id Id of the engineer to remove
     */
    void remove(int id);

    /**
     * Retrieves an engineer from the repository
     *
     * @param id Id of the engineer to retreive
     * @return
     */
    Engineer get(int id);
}
