package com.example.ticketing.controller;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    // POST /api/attendee - register a new attendee
    @PostMapping
    public ResponseEntity<Attendee> registerAttendee(@RequestBody Attendee attendee) {
        Attendee register = attendeeService.registerAttendee(attendee);
        return ResponseEntity.status(201).body(register);
    }
    // GET /api/attendees/{id}/bookings - get all bookings for an attendee
    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getAttendeeBookings(@PathVariable Long id) {
        AttendeeBookingsDTO dto = attendeeService.getAttendeeBookings(id);
        return ResponseEntity.status(200).body(dto);
    }
}
