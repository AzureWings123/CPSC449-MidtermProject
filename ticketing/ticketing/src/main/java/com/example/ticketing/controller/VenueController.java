package com.example.ticketing.controller;


import com.example.ticketing.dto.VenueResponseDTO;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.entity.Venue;
import com.example.ticketing.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    // POST /api/venues - create a new venue
    @PostMapping
    public ResponseEntity<VenueResponseDTO> createVenue(@RequestBody Venue venue) {
        VenueResponseDTO created = venueService.createVenue(venue);
        return ResponseEntity.status(201).body(created);
    }
}
