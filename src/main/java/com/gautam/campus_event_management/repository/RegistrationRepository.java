package com.gautam.campus_event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gautam.campus_event_management.entity.AppUser;
import com.gautam.campus_event_management.entity.Event;
import com.gautam.campus_event_management.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByUserAndEvent(AppUser user, Event event);

    int countByEvent(Event event);
}