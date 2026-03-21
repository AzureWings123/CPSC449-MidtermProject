package com.example.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeBookingsDTO {

    private String attendeeName;
    private List<BookingResponseDTO> bookings;
}
