package com.example.ticketing.service;

import com.example.ticketing.dto.AttendeeBookingsDTO;
import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.repository.AttendeeRepository;
import com.example.ticketing.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepo;

    @Autowired
    private BookingRepository bookingRepo;

    //Gets all bookings for an attendee
    public AttendeeBookingsDTO getBookingsForAttendee(Long attendeeId) {

        Attendee attendee = attendeeRepo.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));

        List<BookingResponseDTO> bookings = bookingRepo.findByAttendee(attendee)
                .stream()
                .map(this::mapBookingToDTO)
                .toList();

        AttendeeBookingsDTO dto = new AttendeeBookingsDTO();
        dto.setAttendeeName(attendee.getName());
        dto.setBookings(bookings);

        return dto;
    }

    private BookingResponseDTO mapBookingToDTO(Booking booking) {
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
