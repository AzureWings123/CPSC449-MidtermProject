# CPSC449-MidtermProject

This project is a backend API for an Event Ticketing System,
similar in concept to Ticketmaster or Eventbrite. The system allows
organizers to create events, the venues where they are held, manage different
types of tickets, and allow attendees to book tickets. The system manages:  
* Organizers: Create and manage events.
* Venues: Locations with limited capacity.
* Events: Held at a venue, hosted by organizer, with multiple ticket types.
* Ticket Types: Different types of ticket (e.g., VIP, Student, and General Admission) with prices and availability.
* Attendees: Users who register and book tickets.
* Bookings: Records ticket purchases, payment status, and booking references.
## Team Members:
Cesar Carrillo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;819760794  
Vanessa Ragan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;888419215  
Hunter Tran&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;886474907
## API Endpoints
### POST /api/organizers
Creates a new organizer.  
#### Request:
![Image of POST Endpoint](screenshots/Image1.png)
#### Response:
![Image of POST Endpoint](screenshots/Image2.png)
***
### POST /api/venues
Creates a new venue.  
#### Request:
![Image of POST Endpoint](screenshots/Image3.png)
#### Response:
![Image of POST Endpoint](screenshots/Image4.png)
***
### POST /api/events
Creates a new event.  
#### Request:
![Image of POST Endpoint](screenshots/Image5.png)
#### Response:
![Image of POST Endpoint](screenshots/Image6.png)
***
### GET /api/events
List of all upcoming events.  
#### Request:
![Image of GET Endpoint](screenshots/Image17.png)
#### Response:
![Image of GET Endpoint](screenshots/Image18.png)
***
### GET /api/events/{id}
Gets event details with ticket types.
#### Request:
![Image of GET Endpoint](screenshots/Image21.png)
#### Response:
![Image of GET Endpoint](screenshots/Image22.png)
***
### POST /api/attendees
Registers a new attendee.  
#### Request:
![Image of POST Endpoint](screenshots/Image7.png)
#### Response:
![Image of POST Endpoint](screenshots/Image8.png)
***
### POST /api/bookings
Books a ticket.  
#### Request:
![Image of POST Endpoint](screenshots/Image9.png)
#### Response:
![Image of POST Endpoint](screenshots/Image10.png)
***
### PUT /api/bookings/{id}/cancel
Cancels a booking.  
#### Request:
![Image of PUT Endpoint](screenshots/Image15.png)
#### Response:
![Image of PUT Endpoint](screenshots/Image16.png)
***
### GET /api/events/{id}/revenue
Gets total confirmed revenue for an event.  
#### Request:
![Image of GET Endpoint](screenshots/Image13.png)
#### Response:
![Image of GET Endpoint](screenshots/Image14.png)
***
### GET /api/attendees/{id}/bookings
Gets all bookings for an attendee.  
#### Request:
![Image of GET Endpoint](screenshots/Image11.png)
#### Response:
![Image of GET Endpoint](screenshots/Image12.png)
***
### POST /api/ticket-types (optional)
Creates a ticket type for an event.
#### Request:
![Image of POST Endpoint](screenshots/Image19.png)
#### Response:
![Image of POST Endpoint](screenshots/Image20.png)
***

