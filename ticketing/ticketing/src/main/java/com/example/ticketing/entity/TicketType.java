package com.example.ticketing.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "TicketType")
@Data
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketTypeId;

    private String name;
    private BigDecimal price;
    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "ticketType")
    private List<Booking> bookings;
}
