package com.example.ticketing.controller;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.service.EventService;
import com.example.ticketing.service.OrganizerService;
import com.example.ticketing.service.VenueService;
import com.example.ticketing.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private VenueService venueService;

    // POST /api/events - creates a new event
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@PathVariable Long organizerId,
                                                        @PathVariable Long venueId,
                                                        @RequestBody Event event) {
        EventResponseDTO dto = eventService.createEvent(organizerId, venueId, event);

        return ResponseEntity.status(201).body(dto);
    }
    // GET /api/events - lists upcoming events

    // GET /api/events/{id} - event details with ticket types

    // GET /api/events/{id}/revenue - total revenue
}
