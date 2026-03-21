package com.example.ticketing.service;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.repository.AttendeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private BookingService bookingService;

    // register attendee
    @Transactional
    public Attendee registerAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    // get attendee bookings
    public AttendeeBookingsDTO getAttendeeBookings(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId).orElse(null);
        if (attendee == null) {
            throw new RuntimeException("Attendee not found");
        }

        return new AttendeeBookingsDTO(
                attendee.getName(),
                bookingService.getBookingsByAttendee(attendeeId)
        );
    }
}