package com.example.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String status;
    private String organizerName;
    private String venueName;
    private List<TicketTypeDTO> ticketTypes;
}
