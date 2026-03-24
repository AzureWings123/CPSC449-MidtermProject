package com.example.ticketing.controller;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.service.EventService;
import com.example.ticketing.service.OrganizerService;
import com.example.ticketing.service.VenueService;
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
    public EventResponseDTO createEvent(@RequestBody Event event,
                                        @RequestParam Long organizerId,
                                        @RequestParam Long venueId) {
        EventResponseDTO created = eventService.createEvent(organizerId, venueId, event);
        return created;
    }

    // GET /api/events - lists upcoming events

    // GET /api/events/{id} - event details with ticket types

    // GET /api/events/{id}/revenue - total revenue
}
