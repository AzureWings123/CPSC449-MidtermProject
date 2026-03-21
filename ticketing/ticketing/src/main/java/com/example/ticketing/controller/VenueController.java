package com.example.ticketing.controller;

import com.example.ticketing.entity.Venue;
import com.example.ticketing.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueRepository venueRepo;

    // POST /api/venues - creates a new venue
    @PostMapping
    public Venue createVenue(@RequestBody Venue venue) {
        return venueRepo.save(venue);
    }
}
