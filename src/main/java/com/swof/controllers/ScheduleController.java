package com.swof.controllers;

import com.swof.interfaces.IScheduleGeneratorService;
import com.swof.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {
    private IScheduleGeneratorService scheduleGeneratorService;

    @Autowired
    public ScheduleController(IScheduleGeneratorService scheduleGeneratorService) {
        this.scheduleGeneratorService = scheduleGeneratorService;
    }

    /**
     * Generate a new schedule
     *
     * @return List of shifts for the new schedule
     */
    @RequestMapping(value = "/api/shifts/{ShiftsPerPeriod}/{ShiftsPerEngineerPerPeriod}", method = RequestMethod.GET)
    public final List<Shift> get(@PathVariable int ShiftsPerPeriod, @PathVariable int ShiftsPerEngineerPerPeriod) {
        return scheduleGeneratorService.generate(ShiftsPerPeriod, ShiftsPerEngineerPerPeriod);
    }
}
