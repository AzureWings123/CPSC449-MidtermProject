package com.example.ticketing.entity;
import com.example.ticketing.enums.PaymentStatus;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false, unique = true)
    private String bookingReference;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

}
