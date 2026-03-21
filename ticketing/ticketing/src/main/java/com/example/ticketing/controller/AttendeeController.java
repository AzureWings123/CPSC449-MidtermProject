package com.example.ticketing.controller;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.repository.AttendeeRepository;
import com.example.ticketing.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private AttendeeRepository attendeeRepo;

    // POST /api/attendees - register a new attendee
    @PostMapping
    public Attendee registerAttendee(@RequestBody Attendee attendee) {
        //TODO
        return attendeeRepo.save(attendee);
    }

    // GET /api/attendees/{id}/bookings - get all bookings for an attendee
    @GetMapping("/{id}/bookings")
    public AttendeeBookingsDTO getBookings(@PathVariable Long id) {
        return attendeeService.getBookingsForAttendee(id);
    }
}
