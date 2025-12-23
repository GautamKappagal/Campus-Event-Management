package com.gautam.campus_event_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.entity.Registration;
import com.gautam.campus_event_management.entity.User;
import com.gautam.campus_event_management.repository.EventRepository;
import com.gautam.campus_event_management.repository.RegistrationRepository;
import com.gautam.campus_event_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepo;
    private final RegistrationRepository regRepo;
    private final UserRepository userRepo;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepo.save(event);
    }

    @GetMapping
    public List<Event> getApprovedEvents() {
        return eventRepo.findByApprovedTrue();
    }

    @PostMapping("/{eventId}/register/{userId}")
    public String register(@PathVariable Long eventId, @PathVariable Long userId) {
        Event event = eventRepo.findById(eventId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        if (regRepo.existsByUserAndEvent(user, event)) {
            return "Already registered";
        }

        regRepo.save(new Registration(null, user, event));
        return "Registered successfully";
    }
}