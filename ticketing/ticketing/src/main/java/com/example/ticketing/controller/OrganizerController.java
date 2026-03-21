package com.example.ticketing.controller;

import com.example.ticketing.entity.Organizer;
import com.example.ticketing.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerRepository organizerRepo;

    // POST /api/organizers - creates a new organizer
    @PostMapping
    public Organizer createOrganizer(@RequestBody Organizer organizer) {
        return organizerRepo.save(organizer);
    }
}
