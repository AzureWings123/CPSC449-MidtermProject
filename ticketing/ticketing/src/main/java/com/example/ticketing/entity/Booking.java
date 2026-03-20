package com.example.ticketing.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String reference;

    private String date;

    private enum Payment {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "attendee_bookings",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private List<Attendee> attendees;

    @ManyToMany
    @JoinTable(
            name = "ticket_types_bookings",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_type_id")
    )
    private List<TicketType> ticket_types;
}
