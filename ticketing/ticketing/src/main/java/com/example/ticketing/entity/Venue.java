package com.example.ticketing.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Venue")
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    private String name;
    private String address;
    private String city;
    private int totalCapacity;

    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}
