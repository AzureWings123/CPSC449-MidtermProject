package com.example.ticketing.service;

import com.example.ticketing.entity.Venue;
import com.example.ticketing.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // create venue
    @Transactional
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}