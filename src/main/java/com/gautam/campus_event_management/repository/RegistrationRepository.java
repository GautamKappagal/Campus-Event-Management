package com.gautam.campus_event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.entity.Registration;
import com.gautam.campus_event_management.entity.User;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByUserAndEvent(User user, Event event);
}