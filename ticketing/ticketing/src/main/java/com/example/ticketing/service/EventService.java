package com.example.ticketing.service;

import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.dto.RevenueDTO;
import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.enums.EventStatus;
import com.example.ticketing.repository.BookingRepository;
import com.example.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private BookingRepository bookingRepo;

    //Gets all upcoming events
    public List<EventResponseDTO> getUpcomingEvents() {
        List<Event> events = eventRepo.findByStatus(EventStatus.UPCOMING);
        return events.stream()
                .map(this::mapToDTO)
                .toList();
    }

    //Gets event details by ID
    public EventResponseDTO getEventDetails(Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return mapToDTO(event);
    }

    //Calculates total revenue for event
    public RevenueDTO calculateRevenue(Long eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        BigDecimal revenue = bookingRepo.calculateRevenue(eventId);
        if(revenue == null) {
            revenue = BigDecimal.ZERO;
        }

        RevenueDTO dto = new RevenueDTO();
        dto.setEventTitle(event.getTitle());
        dto.setTotalRevenue(revenue);
        return dto;
    }

    //Maps Event entity to EventResponseDTO
    private EventResponseDTO mapToDTO(Event event) {

        EventResponseDTO dto = new EventResponseDTO();

        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setStatus(event.getStatus().name());
        dto.setOrganizerName(event.getOrganizer().getName());
        dto.setVenueName(event.getVenue().getName());

        List<TicketTypeDTO> tickets = event.getTicketTypes().stream()
                .map(ticket -> {
                    TicketTypeDTO ticketDTO = new TicketTypeDTO();
                    ticketDTO.setName(ticket.getName());
                    ticketDTO.setPrice(ticket.getPrice());
                    ticketDTO.setQuantityAvailable(ticket.getQuantityAvailable());
                    return ticketDTO;
                }).toList();

        dto.setTicketTypes(tickets);

        return dto;
    }
}
