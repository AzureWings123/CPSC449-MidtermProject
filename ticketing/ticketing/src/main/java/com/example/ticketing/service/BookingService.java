package com.example.ticketing.service;

import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.enums.PaymentStatus;
import com.example.ticketing.repository.AttendeeRepository;
import com.example.ticketing.repository.BookingRepository;
import com.example.ticketing.repository.TicketTypeRepository;
import com.example.ticketing.dto.BookingResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private TicketTypeRepository ticketRepo;

    @Autowired
    private AttendeeRepository attendeeRepo;

    //Book ticket for attendee
    @Transactional
    public BookingResponseDTO bookTicket(Long attendeeId, Long ticketTypeId) {

        Attendee attendee = attendeeRepo.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));

        TicketType ticket = ticketRepo.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        if(ticket.getQuantityAvailable() <= 0)
            throw new RuntimeException("Sorry, this ticket is sold out.");

        if(bookingRepo.existsByAttendeeAndTicketType(attendee, ticket))
            throw new RuntimeException("You have already booked this ticket type.");

        //Decrement inventory
        ticket.setQuantityAvailable(ticket.getQuantityAvailable() - 1);
        ticketRepo.save(ticket);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticket);
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        booking.setBookingDate(LocalDateTime.now());
        booking = bookingRepo.save(booking);


        String reference = String.format(
                "TKT-%d-%05d",
                LocalDateTime.now().getYear(),
                booking.getBookingId()
        );

        return mapToDTO(booking);
    }

    //Cancel a booking
    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }

        //Cancel and restore ticket quantity
        booking.setPaymentStatus(PaymentStatus.CANCELLED);
        TicketType ticket = booking.getTicketType();
        ticket.setQuantityAvailable(ticket.getQuantityAvailable() + 1);
        ticketRepo.save(ticket);

        booking = bookingRepo.save(booking);

        return mapToDTO(booking);
    }

    //Map Booking entity to BookingResponseDTO
    private BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingReference(booking.getBookingReference());
        dto.setBookingDate(booking.getBookingDate());
        dto.setPaymentStatus(booking.getPaymentStatus().name());
        dto.setAttendeeName(booking.getAttendee().getName());
        dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
        dto.setTicketTypeName(booking.getTicketType().getName());
        dto.setPrice(booking.getTicketType().getPrice());

        return dto;
    }
}
