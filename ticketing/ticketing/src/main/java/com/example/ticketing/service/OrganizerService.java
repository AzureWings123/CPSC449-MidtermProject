package com.example.ticketing.service;

import com.example.ticketing.entity.Organizer;
import com.example.ticketing.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    // create organizer
    @Transactional
    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}