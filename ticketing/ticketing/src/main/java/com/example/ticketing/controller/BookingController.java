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

    // PUT /api/bookings/{id}/cancel - cancel a booking
}
