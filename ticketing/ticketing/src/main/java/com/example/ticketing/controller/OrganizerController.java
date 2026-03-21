package com.example.ticketing.controller;


import com.example.ticketing.entity.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    /* Uncomment after OrganizerService is made
    @Autowired
    private OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer){
        Organizer created = organizerService.save(organizer);
        return ResponseEntity.status(201).body(created);
    } */
}
