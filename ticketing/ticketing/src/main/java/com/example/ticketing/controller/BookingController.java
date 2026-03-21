package com.example.ticketing.controller;

import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST /api/bookings - book a ticket
    @PostMapping
    public BookingResponseDTO bookTicket(@RequestParam Long attendeeId,
                                         @RequestParam Long ticketTypeId) {
        return bookingService.bookTicket(attendeeId, ticketTypeId);
    }

    // PUT /api/bookings/{id}/cancel - cancel a booking
    @PutMapping("/{id}/cancel")
    public BookingResponseDTO cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
