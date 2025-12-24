package com.gautam.campus_event_management.service;

import org.springframework.stereotype.Service;

import com.gautam.campus_event_management.entity.AppUser;
import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.entity.Registration;
import com.gautam.campus_event_management.exception.BadRequestException;
import com.gautam.campus_event_management.exception.ResourceNotFoundException;
import com.gautam.campus_event_management.repository.AppUserRepository;
import com.gautam.campus_event_management.repository.EventRepository;
import com.gautam.campus_event_management.repository.RegistrationRepository;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final AppUserRepository appUserRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            EventRepository eventRepository,
            AppUserRepository appUserRepository) {

        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    public void registerUser(Long eventId, Long userId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (event.getApproved() == null || !event.getApproved()) {
            throw new BadRequestException("Event not approved yet");
        }

        int currentRegistrations = registrationRepository.countByEvent(event);
        if (currentRegistrations >= event.getCapacity()) {
            throw new BadRequestException("Event is full");
        }

        boolean alreadyRegistered = registrationRepository.existsByUserAndEvent(user, event);
        if (alreadyRegistered) {
            throw new BadRequestException("User already registered for this event");
        }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);

        registrationRepository.save(registration);
    }
}
