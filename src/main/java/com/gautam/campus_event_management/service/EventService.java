package com.gautam.campus_event_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gautam.campus_event_management.dto.EventCreateDTO;
import com.gautam.campus_event_management.entity.Club;
import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.exception.BadRequestException;
import com.gautam.campus_event_management.exception.ResourceNotFoundException;
import com.gautam.campus_event_management.repository.ClubRepository;
import com.gautam.campus_event_management.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    public EventService(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    public Event createEvent(EventCreateDTO dto) {

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new BadRequestException("Event title cannot be empty");
        }

        if (dto.getCapacity() <= 0) {
            throw new BadRequestException("Event capacity must be greater than 0");
        }

        @SuppressWarnings("null")
        Club club = clubRepository.findById(dto.getClubId())
                .orElseThrow(() -> new ResourceNotFoundException("Club not found"));

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setCapacity(dto.getCapacity());
        event.setApproved(false);
        event.setClub(club);

        return eventRepository.save(event);
    }

    public List<Event> getApprovedEvents() {
        return eventRepository.findByApprovedTrue();
    }

    public List<Event> getPendingEvents() {
        return eventRepository.findByApprovedFalse();
    }
}