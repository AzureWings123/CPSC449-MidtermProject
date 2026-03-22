package com.example.ticketing.controller;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeServices;

    // POST /api/attendee - register a new attendee
    @PostMapping
    public Attendee registerAttendee(@RequestBody Attendee attendee) {
        Attendee register = attendeeServices.registerAttendee(attendee);
        return register;
    }


    // GET /api/attendee/{id}/bookings - get all bookings for an attendee
}
