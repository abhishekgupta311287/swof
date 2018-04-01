package com.swof.controllers;

import com.swof.interfaces.IScheduleGeneratorService;
import com.swof.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class deal with the schedule generation api
 */
@RestController
public class ScheduleController {
    private IScheduleGeneratorService scheduleGeneratorService;

    @Autowired
    public ScheduleController(IScheduleGeneratorService scheduleGeneratorService) {
        this.scheduleGeneratorService = scheduleGeneratorService;
    }


    /**
     * @param ShiftsPerPeriod number of shifts per period
     * @param ShiftsPerEngineerPerPeriod number of shifts and engineer to do at max
     * @return the list of shift
     */
    @RequestMapping(value = "/api/shifts/{ShiftsPerPeriod}/{ShiftsPerEngineerPerPeriod}", method = RequestMethod.GET)
    public final List<Shift> get(@PathVariable int ShiftsPerPeriod, @PathVariable int ShiftsPerEngineerPerPeriod) {
        return scheduleGeneratorService.generate(ShiftsPerPeriod, ShiftsPerEngineerPerPeriod);
    }
}
