package com.swof.controllers;

import com.swof.interfaces.IEngineerPoolList;
import com.swof.interfaces.IScheduleGenerator;
import com.swof.model.Day;
import com.swof.model.Engineer;
import com.swof.model.Schedule;
import com.swof.model.Shift;
import com.swof.utils.Constants;
import com.swof.utils.DateUtils;
import com.swof.utils.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with the view layer for presenting schedule and engineers list
 */
@RestController
public class ViewController {
    private IEngineerPoolList engineerRepository;
    private IScheduleGenerator scheduleGeneratorService;

    @Autowired
    public ViewController(IEngineerPoolList engineerRepository, IScheduleGenerator scheduleGeneratorService) {
        this.engineerRepository = engineerRepository;
        this.scheduleGeneratorService = scheduleGeneratorService;
    }

    /**
     * @return day wise list of schedule
     */
    @RequestMapping(value = "/schedule")
    public Schedule getSchedule() {
        ArrayList<Shift> shifts = scheduleGeneratorService.generate(Constants.SHIFT_PER_PERIOD, Constants.SHIFTS_PER_ENGINEER_PER_PERIOD);

        int shiftsPerDay = Constants.SHIFT_PER_PERIOD / Constants.SHIFT_DAYS;

        ArrayList<Day> days = new ArrayList<>();

        Date now = new Date();

        for (int i = 0; i < Constants.SHIFT_DAYS; i++) {
            Day day = new Day();
            List<Shift> shiftList = shifts.stream().skip(i * shiftsPerDay).limit(shiftsPerDay).collect(Collectors.toList());
            day.setShifts(shiftList);
            day.setWeekNumber(i < 5 ? 1 : 2);

            long dateMillis = DateUtils.getDate(now);
            now = new Date(dateMillis);

            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String dayStr = simpleDateformat.format(now);

            simpleDateformat = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = simpleDateformat.format(now);

            day.setDate(dateStr);
            day.setName(dayStr);
            days.add(day);

            dateMillis = dateMillis + (24 * 60 * 60 * 1000);
            now = new Date(dateMillis);
        }

        Schedule schedule = new Schedule();
        schedule.setDays(days);

        return schedule;
    }

    /**
     * @return html page that displays the schedule and list of engineers
     */
    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        ArrayList<Engineer> engineers = engineerRepository.getAll();

        return HtmlUtils.getHtml(engineers, getSchedule().getDays());
    }
}
