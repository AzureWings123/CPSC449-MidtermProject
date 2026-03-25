package com.example.ticketing.controller;

import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

    // POST /api/ticket-types - creates ticket type for event
    @PostMapping
    public ResponseEntity<TicketTypeDTO> createTicketType(@RequestParam Long eventId,
                                                       @RequestBody TicketType ticketType) {

        TicketTypeDTO created = ticketTypeService.createTicketType(eventId, ticketType);

        return ResponseEntity.status(201).body(created);
    }
}
