package com.gautam.campus_event_management.controller;

import com.gautam.campus_event_management.dto.EventCreateDTO;
import com.gautam.campus_event_management.entity.Club;
import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.repository.ClubRepository;
import com.gautam.campus_event_management.repository.EventRepository;
import com.gautam.campus_event_management.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    private final RegistrationService registrationService;

    public EventController(
            EventRepository eventRepository,
            ClubRepository clubRepository,
            RegistrationService registrationService) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.registrationService = registrationService;
    }

    @PostMapping
    public Event createEvent(@RequestBody EventCreateDTO dto) {
        Club club = clubRepository.findById(dto.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found"));

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setCapacity(dto.getCapacity());
        event.setApproved(false);
        event.setClub(club);

        return eventRepository.save(event);
    }

    @GetMapping
    public Iterable<Event> getApprovedEvents() {
        return eventRepository.findByApprovedTrue();
    }

    @PostMapping("/{eventId}/register/{userId}")
    public String register(@PathVariable Long eventId, @PathVariable Long userId) {
        registrationService.registerUser(eventId, userId);
        return "User registered successfully!";
    }
}