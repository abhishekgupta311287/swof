package com.swof.controllers;

import com.swof.interfaces.IEngineerPoolList;
import com.swof.model.Engineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class exposes the api to deal with the list of engineers
 */
@RestController
public class EngineerController {

    private IEngineerPoolList engineerRepository;

    @Autowired
    public EngineerController(IEngineerPoolList engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    /**
     * @return List of engineers added in advance
     */

//    @RequestMapping(value = "/api/engineers", method = RequestMethod.GET)
    @RequestMapping("/api/engineers")
    public final List<Engineer> getEngineers() {
        return engineerRepository.getAll();
    }

    /**
     * @param id Identifier of the engineer to be returned
     * @return The engineer with the specified id
     */

    @RequestMapping(value = "/api/engineer/{id}", method = RequestMethod.GET)
    public final Engineer getEngineer(@PathVariable int id) {
        Engineer engineer = engineerRepository.get(id);
        return engineer;
    }

    /**
     * Adds a new engineer with the given name
     * @param name Name of the engineer.
     * @return Id of the new engineer
     */
    @RequestMapping(value = "/api/add/engineer/{name}", method = RequestMethod.PUT)
    public final int addEngineer(@PathVariable String name) {
        Engineer engineer = new Engineer();
        engineer.setName(name);
        return engineerRepository.add(engineer);
    }

    /**
     * deletes the engineer with the given identifier from the predefined array list
     *
     * @param id Identifier of the engineer to delete
     * @return OK if deleted
     */

    @RequestMapping(value = "/api/delete/engineer/{id}", method = RequestMethod.DELETE)
    public final boolean deleteEngineer(@PathVariable int id) {
        engineerRepository.remove(id);
        return true;
    }
}
