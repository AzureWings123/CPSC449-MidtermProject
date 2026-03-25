package com.example.ticketing.service;

import com.example.ticketing.dto.VenueResponseDTO;
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
    public VenueResponseDTO createVenue(Venue venue) {

        if(venue.getName() == null || venue.getName().isBlank()) {
            throw new IllegalArgumentException("Venue name is required.");
        }

        if(venue.getAddress() == null || venue.getAddress().isBlank()) {
            throw new IllegalArgumentException("Venue address is required.");
        }

        if(venue.getCity() == null || venue.getCity().isBlank()) {
            throw new IllegalArgumentException("Venue city is required.");
        }

        if(venue.getTotalCapacity() <= 0) {
            throw new IllegalArgumentException("Venue capacity must be greater than 0.");
        }

        Venue saved = venueRepository.save(venue);

        return mapToDTO(saved);
    }

    private VenueResponseDTO mapToDTO(Venue venue) {
        VenueResponseDTO dto = new VenueResponseDTO();
        dto.setVenueId(venue.getVenueId());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCity(venue.getCity());
        dto.setTotalCapacity(venue.getTotalCapacity());
        return dto;
    }
}