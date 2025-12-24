package com.gautam.campus_event_management.repository;

import java.util.List;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gautam.campus_event_management.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByApprovedTrue();
    List<Event> findByApprovedFalse();
}