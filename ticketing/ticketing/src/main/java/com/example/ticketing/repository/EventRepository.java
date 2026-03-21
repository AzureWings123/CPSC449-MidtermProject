package com.example.ticketing.repository;

import com.example.ticketing.entity.Event;
import com.example.ticketing.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigDecimal;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //Finds all events by status mainly used for upcoming events
    List<Event> findByStatus(EventStatus status);
}
