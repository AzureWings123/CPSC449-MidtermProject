package com.example.ticketing.service;

import com.example.ticketing.dto.OrganizerResponseDTO;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    // create organizer
    @Transactional
    public OrganizerResponseDTO createOrganizer(Organizer organizer) {

        if(organizer.getName() == null || organizer.getName().isBlank()) {
            throw new NullPointerException("Organizer name is required.");
        }

        if(organizer.getEmail() == null || organizer.getEmail().isBlank()) {
            throw new NullPointerException("Organizer email is required.");
        }

        if(organizerRepository.existsByEmail(organizer.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Organizer saved = organizerRepository.save(organizer);

        return mapToDTO(saved);
    }

    private OrganizerResponseDTO mapToDTO(Organizer organizer) {
        OrganizerResponseDTO dto = new OrganizerResponseDTO();
        dto.setOrganizerId(organizer.getOrganizerId());
        dto.setName(organizer.getName());
        dto.setEmail(organizer.getEmail());
        dto.setPhone(organizer.getPhone());
        return dto;
    }
}