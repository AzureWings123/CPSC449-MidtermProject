package com.example.ticketing.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // optional
    private String description;

    @Column(nullable = false)
    private String event_date;

    // Enum
    private enum Status { // If I try and declare it outside, throws an error because the filename is Event.java
        UPCOMING,
        ONGOING,
        CANCELLED,
        COMPLETED
    }
    @Enumerated(EnumType.STRING)
    private Status status;


}
