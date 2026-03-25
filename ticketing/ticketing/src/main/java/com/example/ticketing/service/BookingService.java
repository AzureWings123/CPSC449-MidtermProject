package com.example.ticketing.service;

import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.enums.PaymentStatus;
import com.example.ticketing.repository.AttendeeRepository;
import com.example.ticketing.repository.BookingRepository;
import com.example.ticketing.repository.TicketTypeRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    // book a ticket
    @Transactional
    public BookingResponseDTO bookTicket(Long attendeeId, Long ticketTypeId) {

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId).orElse(null);
        if (ticketType == null) {
            //throw new NullPointerException("Ticket type not found.")
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket type not found.");
        }

        if (ticketType.getQuantityAvailable() <= 0) {
            throw new IllegalArgumentException("Sorry, this ticket type is sold out.");
        }

        Attendee attendee = attendeeRepository.findById(attendeeId).orElse(null);
        if (attendee == null) {
            //throw new RuntimeException("Attendee not found");
            throw new NullPointerException("Attendee not found");
        }

        // check if attendee already booked this
        boolean exists = bookingRepository.existsByAttendeeAndTicketType(attendee, ticketType);
        if (exists) {
            throw new IllegalArgumentException("You have already booked this ticket type.");
        }

        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        booking.setBookingReference("TKT-TEMP");
        booking = bookingRepository.save(booking);

        // generate reference after saving to get the id
        String ref = String.format("TKT-%d-%05d", booking.getBookingDate().getYear(), booking.getBookingId());
        booking.setBookingReference(ref);
        booking = bookingRepository.save(booking);

        return toDTO(booking);
    }

    // cancel a booking
    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new NullPointerException("Booking not found");
        }

        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new IllegalArgumentException("Booking is already cancelled.");
        }

        booking.setPaymentStatus(PaymentStatus.CANCELLED);

        // restore quantity
        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        ticketTypeRepository.save(ticketType);

        bookingRepository.save(booking);
        return toDTO(booking);
    }

    // get bookings by attendee
    public List<BookingResponseDTO> getBookingsByAttendee(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId).orElse(null);
        if (attendee == null) {
            throw new NullPointerException("Attendee not found");
        }

        return bookingRepository.findByAttendee(attendee)
                .stream()
                .map(booking -> toDTO(booking))
                .collect(Collectors.toList());
    }

    // map to DTO
    private BookingResponseDTO toDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getBookingReference(),
                booking.getBookingDate(),
                booking.getPaymentStatus().name(),
                booking.getAttendee().getName(),
                booking.getTicketType().getEvent().getTitle(),
                booking.getTicketType().getName(),
                booking.getTicketType().getPrice()
        );
    }
}