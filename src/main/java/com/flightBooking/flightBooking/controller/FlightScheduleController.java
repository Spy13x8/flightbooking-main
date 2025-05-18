package com.flightBooking.flightBooking.controller;

import com.flightBooking.flightBooking.services.FlightSchedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/flightSchedule")
public class FlightScheduleController {
    private Map<String, FlightSchedule> scheduleMap = new HashMap<>();

    @GetMapping()
    public List<FlightSchedule> getAllFlights(@RequestParam(defaultValue = "asc") String sort) {
        List<FlightSchedule> flights = new ArrayList<>(scheduleMap.values());
        if (sort.equalsIgnoreCase("desc")) {
            flights.sort(Comparator.comparing(FlightSchedule::getDate).reversed());
        } else {
            flights.sort(Comparator.comparing(FlightSchedule::getDate));
        }
        return flights;
    }



    @PostMapping
    public String createSchedule(@RequestBody FlightSchedule newSchedule) {
        scheduleMap.put(newSchedule.getId(), newSchedule);
        return "Schedule ho gaya!";
    }

    @GetMapping("id/{myId}")
    public FlightSchedule getScheduleById(@PathVariable String myId) {
        return scheduleMap.get(myId);
    }

}
