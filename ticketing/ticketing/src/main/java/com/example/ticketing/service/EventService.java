package com.example.ticketing.service;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.dto.RevenueDTO;
import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.entity.Venue;
import com.example.ticketing.enums.EventStatus;
import com.example.ticketing.repository.BookingRepository;
import com.example.ticketing.repository.EventRepository;
import com.example.ticketing.repository.OrganizerRepository;
import com.example.ticketing.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // create event
    @Transactional
    public EventResponseDTO createEvent(Long organizerId, Long venueId, Event event) {
        Organizer organizer = organizerRepository.findById(organizerId).orElse(null);
        if (organizer == null) {
            throw new RuntimeException("Organizer not found");
        }

        Venue venue = venueRepository.findById(venueId).orElse(null);
        if (venue == null) {
            throw new RuntimeException("Venue not found");
        }

        event.setOrganizer(organizer);
        event.setVenue(venue);
        event.setStatus(EventStatus.UPCOMING);

        Event newEvent = eventRepository.save(event);
        return toDTO(newEvent);
    }

    // get all upcoming events
    public List<EventResponseDTO> getUpcomingEvents() {
        return eventRepository.findByStatus(EventStatus.UPCOMING)
                .stream()
                .map(event -> toDTO(event))
                .collect(Collectors.toList());
    }

    // get event by id
    public EventResponseDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new RuntimeException("Event not found");
        }
        return toDTO(event);
    }

    // get revenue for an event
    public RevenueDTO getEventRevenue(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new RuntimeException("Event not found");
        }

        BigDecimal revenue = bookingRepository.calculateRevenue(eventId);
        if (revenue == null) {
            revenue = BigDecimal.ZERO;
        }

        return new RevenueDTO(event.getTitle(), revenue);
    }

    // map to DTO
    private EventResponseDTO toDTO(Event event) {
        List<TicketTypeDTO> ticketTypes = event.getTicketTypes() == null ? List.of() :
                event.getTicketTypes().stream()
                        .map(ticketType -> new TicketTypeDTO(
                                ticketType.getName(),
                                ticketType.getPrice(),
                                ticketType.getQuantityAvailable()))
                        .collect(Collectors.toList());

        return new EventResponseDTO(
                event.getTitle(),
                event.getDescription(),
                event.getEventDate(),
                event.getStatus().name(),
                event.getOrganizer().getName(),
                event.getVenue().getName(),
                ticketTypes
        );
    }
}