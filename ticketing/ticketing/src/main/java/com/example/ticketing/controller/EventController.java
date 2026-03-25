package com.example.ticketing.controller;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.dto.RevenueDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // POST /api/events - creates a new event
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestParam Long organizerId,
                                                        @RequestParam Long venueId,
                                                        @RequestBody Event event) {
        EventResponseDTO created = eventService.createEvent(organizerId, venueId, event);
        return ResponseEntity.status(201).body(created);
    }

    // GET /api/events - lists upcoming events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents() {
        List<EventResponseDTO> response = eventService.getUpcomingEvents();
        return ResponseEntity.status(200).body(response);
    }

    // GET /api/events/{id} - event details with ticket types
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        EventResponseDTO response = eventService.getEventById(id);
        return ResponseEntity.status(200).body(response);
    }

    // GET /api/events/{id}/revenue - total revenue
    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getEventRevenue(@PathVariable Long id) {
        RevenueDTO response = eventService.getEventRevenue(id);
        return ResponseEntity.status(200).body(response);
    }
}
