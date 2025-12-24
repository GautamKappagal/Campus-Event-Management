package com.gautam.campus_event_management.controller;

import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.repository.EventRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EventRepository eventRepository;

    public AdminController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending-events")
    public Iterable<Event> getPendingEvents() {
        return eventRepository.findByApprovedFalse();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approve/{id}")
    public String approveEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setApproved(true);
        eventRepository.save(event);

        return "Event approved";
    }
}