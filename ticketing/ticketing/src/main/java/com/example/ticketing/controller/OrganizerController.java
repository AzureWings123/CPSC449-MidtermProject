package com.example.ticketing.controller;


import com.example.ticketing.dto.OrganizerResponseDTO;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    // POST /api/organizers - creates a new organizer
    @PostMapping
    public ResponseEntity<OrganizerResponseDTO> createOrganizer(@RequestBody Organizer organizer){
        OrganizerResponseDTO created = organizerService.createOrganizer(organizer);
        return ResponseEntity.status(201).body(created);
    }
}
