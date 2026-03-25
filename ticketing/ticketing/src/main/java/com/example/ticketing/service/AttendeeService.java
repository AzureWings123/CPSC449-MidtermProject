package com.example.ticketing.service;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.dto.AttendeeResponseDTO;
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
    public AttendeeResponseDTO registerAttendee(Attendee attendee) {

        if(attendee.getEmail() == null || attendee.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }

        if(attendee.getName() == null || attendee.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }

        if(attendeeRepository.existsByEmail(attendee.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }
        Attendee saved = attendeeRepository.save(attendee);

        return mapToDTO(saved);
    }

    // get attendee bookings
    public AttendeeBookingsDTO getAttendeeBookings(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId).orElse(null);
        if (attendee == null) {
            throw new NullPointerException("Attendee not found");
        }

        return new AttendeeBookingsDTO(
                attendee.getName(),
                bookingService.getBookingsByAttendee(attendeeId)
        );
    }

    private AttendeeResponseDTO mapToDTO(Attendee attendee) {
        AttendeeResponseDTO dto = new AttendeeResponseDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        return dto;
    }
}