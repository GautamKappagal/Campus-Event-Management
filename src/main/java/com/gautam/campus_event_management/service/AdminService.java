package com.gautam.campus_event_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.exception.ResourceNotFoundException;
import com.gautam.campus_event_management.repository.EventRepository;

@Service
public class AdminService {
    private final EventRepository eventRepository;

    public AdminService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getPendingEvents() {
        return eventRepository.findByApprovedFalse();
    }

    public void approveEvent(Long eventId) {
        @SuppressWarnings("null")
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        event.setApproved(true);
        eventRepository.save(event);
    }
}
