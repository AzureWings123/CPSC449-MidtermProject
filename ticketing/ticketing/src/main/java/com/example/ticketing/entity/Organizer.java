package com.example.ticketing.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="Organizer")
@Data
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
}
