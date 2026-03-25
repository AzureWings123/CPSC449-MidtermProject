package com.example.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerResponseDTO {
    private Long organizerId;
    private String name;
    private String email;
    private String phone;
}
