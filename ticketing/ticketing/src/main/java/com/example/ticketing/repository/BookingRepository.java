package com.example.ticketing.repository;

import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    //Checks if attendee already booked specific ticket type
    boolean existsByAttendeeAndTicketType(Attendee attendee, TicketType ticketType);

    //Finds all booking for specific attendant
    List<Booking> findByAttendee(Attendee attendee);

    //Query that calculates total revenue for an event
    @Query("SELECT SUM(b.ticketType.price) FROM Booking b " +
            "WHERE b.ticketType.event.eventId = :eventId " +
            "AND b.paymentStatus = 'CONFIRMED'")
    BigDecimal calculateRevenue(Long eventId);
}
