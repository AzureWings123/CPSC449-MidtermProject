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


}
