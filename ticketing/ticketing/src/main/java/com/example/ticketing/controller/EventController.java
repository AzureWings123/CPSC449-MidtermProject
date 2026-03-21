package com.example.ticketing.controller;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.dto.RevenueDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.repository.EventRepository;
import com.example.ticketing.repository.OrganizerRepository;
import com.example.ticketing.repository.VenueRepository;
import com.example.ticketing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private OrganizerRepository organizerRepo;

    @Autowired
    private VenueRepository venueRepo;

    // POST /api/events - creates new event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        //Validate organizer and venue exist
        //TODO
        return eventRepo.save(event);
    }

    // GET /api/events - lists upcoming events
    @GetMapping
    List<EventResponseDTO> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    // GET /api/events/{id} - event details with ticket types
    @GetMapping("/{id}")
    public EventResponseDTO getEventDetails(@PathVariable Long id) {
        return eventService.getEventDetails(id);
    }

    // GET /api/events/{id}/revenue - total revenue
    @GetMapping("/{id}/revenue")
    public RevenueDTO getRevenue(@PathVariable Long id) {
        return eventService.calculateRevenue(id);
    }
}
