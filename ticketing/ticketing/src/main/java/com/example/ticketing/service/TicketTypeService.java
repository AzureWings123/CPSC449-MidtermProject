package com.example.ticketing.service;

import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.repository.EventRepository;
import com.example.ticketing.repository.TicketTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    // creates ticket type
    @Transactional
    public TicketTypeDTO createTicketType(Long eventId, TicketType ticketType) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NullPointerException("Event not found."));

        ticketType.setEvent(event);
        TicketType saved = ticketTypeRepository.save(ticketType);
        if(ticketType.getPrice().compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return mapToDTO(saved);
    }

    // maps to DTO
    private TicketTypeDTO mapToDTO(TicketType ticketType) {
        return new TicketTypeDTO(
                ticketType.getName(),
                ticketType.getPrice(),
                ticketType.getQuantityAvailable()
        );
    }
}
