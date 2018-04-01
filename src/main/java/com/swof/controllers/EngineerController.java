package com.swof.controllers;

import com.swof.interfaces.IEngineerRepository;
import com.swof.model.Engineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EngineerController {

    private IEngineerRepository engineerRepository;

    @Autowired
    public EngineerController(IEngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    /**
     * Retrieves all Engineers
     *
     * @return List of engineers
     */

//    @RequestMapping(value = "/api/engineers", method = RequestMethod.GET)
    @RequestMapping("/api/engineers")
    public final List<Engineer> getEngineers() {
        return engineerRepository.getAll();
    }

    /**
     * Gets a single engineer with the matching id
     *
     * @param id Identifier of the engineer to return
     * @return The engineer with the specified Id
     */

    @RequestMapping(value = "/api/engineer/{id}", method = RequestMethod.GET)
    public final Engineer getEngineer(@PathVariable int id) {
        Engineer engineer = engineerRepository.get(id);
        return engineer;
    }

    /**
     * Adds a new engineer with the given name
     * <p>
     * Note that the name must be quoted if using Swagger
     *
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
     * Removes the engineer with the given identifier from the repository
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
