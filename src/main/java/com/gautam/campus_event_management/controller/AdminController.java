package com.gautam.campus_event_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EventRepository eventRepo;

    @GetMapping("/pending-events")
    public List<Event> pendingEvents() {
        return eventRepo.findAll().stream().filter(e -> !e.getApproved()).toList();
    }

    @PostMapping("/approve/{id}")
    public Event approve(@PathVariable Long id) {
        Event event = eventRepo.findById(id).orElseThrow();
        event.setApproved(true);
        return eventRepo.save(event);
    }
}