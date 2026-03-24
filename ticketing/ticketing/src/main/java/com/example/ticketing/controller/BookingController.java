package com.example.ticketing.controller;

import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST /api/bookings - book a ticket
    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTicket(
            @RequestParam Long attendeeId,
            @RequestParam Long ticketTypeId) {

        BookingResponseDTO dto = bookingService.bookTicket(attendeeId, ticketTypeId);

        return ResponseEntity.status(201).body(dto);
    }
    // PUT /api/bookings/{id}/cancel - cancel a booking
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long id) {
        BookingResponseDTO dto = bookingService.cancelBooking(id);

        return ResponseEntity.status(200).body(dto);
    }
}
