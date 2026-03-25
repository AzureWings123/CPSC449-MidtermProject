package com.example.ticketing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptions {

    // For handling when the no entity with given id exists
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNotFound(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /* Handles when body is missing a field and
        - Booking
            - no more tickets are available to book
            - if person already booked ticket
            - a booking you are trying to cancel was already canceled
        - Attendee
            - if email provided is already in use
        - Organizer
            - if email provided is already in use
        - Ticket Type
            - if price of ticket is negative
        - Venue
            - If venue capacity is negative
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
