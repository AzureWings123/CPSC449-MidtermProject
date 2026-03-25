package com.example.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponseDTO {
    private Long venueId;
    private String name;
    private String address;
    private String city;
    private int totalCapacity;
}
